package persistence.dataaccessobjects;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import model.finance.accounts.AssetAccount;
import model.finance.accounts.FinancialAccount;
import model.finance.accounts.LiabilityAccount;
import model.finance.financeModel.FinanceModel;
import model.finance.financeModel.SiteFinanceModel;
import org.bson.json.JsonReader;
import persistence.IPersistence;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FinanceModelDAO implements IPersistence<SiteFinanceModel> {

    private String filePath;
    private Gson gson = new Gson();


    public FinanceModelDAO (String filePath){
        this.filePath = filePath;
        gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonSerializer<LocalDate>() {
            @Override
            public JsonElement serialize(LocalDate date, Type type, JsonSerializationContext jsonSerializationContext) {
                JsonObject obj = new JsonObject();
                obj.addProperty("year", date.getYear());
                obj.addProperty("month", date.getMonthValue());
                obj.addProperty("day", date.getDayOfMonth());
                return obj;
            }
        }).registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
            @Override
            public LocalDate deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
                JsonObject obj = jsonElement.getAsJsonObject();
                int year = obj.get("year").getAsInt();
                int month = obj.get("month").getAsInt();
                int day = obj.get("day").getAsInt();
                return LocalDate.of(year, month, day);
            }
        }).registerTypeAdapter(FinancialAccount.class, new JsonSerializer<FinancialAccount>() {
            @Override
            public JsonElement serialize(FinancialAccount account, Type type, JsonSerializationContext jsonSerializationContext) {
                if (account instanceof LiabilityAccount liabilityAccount){
                    JsonObject e = gson.toJsonTree(liabilityAccount).getAsJsonObject();
                    e.addProperty("type", 2);
                    return e;
                } else if (account instanceof AssetAccount assetAccount){
                    JsonObject e = gson.toJsonTree(assetAccount).getAsJsonObject();
                    e.addProperty("type", 1);
                    return e;
                }
                return null;
            }
        }).registerTypeAdapter(FinancialAccount.class, new JsonDeserializer<FinancialAccount>() {
            @Override
            public FinancialAccount deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                JsonObject jsonObject = json.getAsJsonObject();
                int type = jsonObject.get("type").getAsInt();
                switch (type) {
                    case 1 -> {return context.deserialize(jsonObject, AssetAccount.class); }
                    case 2 -> {return context.deserialize(jsonObject, LiabilityAccount.class); }
                    default -> {throw new JsonParseException("Invalid FinancialAccount type: " + type); }
                    }
                }

        }).create();
    }


    @Override
    public void save(List<SiteFinanceModel> items) {
        Map<Long, SiteFinanceModel> asMap = new HashMap<>();
        items.forEach(item -> asMap.put(item.getId(), item));
        String json = gson.toJson(asMap);
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(filePath));
            bw.write(json);
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Map<Long, SiteFinanceModel> getAllMap() {
        try {
            String json = new String(Files.readAllBytes(Path.of(filePath)));
            return gson.fromJson(json, new TypeToken<Map<Long, SiteFinanceModel>>() {}.getType());
        } catch (Exception e){
            e.printStackTrace();
        }
        return new HashMap<>();
    }

}
