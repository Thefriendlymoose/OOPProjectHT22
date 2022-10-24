package persistencetests;

import com.google.gson.*;
import model.article.Article;
import model.article.ArticleCategory;
import model.article.ArticleStatus;
import org.junit.jupiter.api.Test;
import persistence.SerializeBuilder;
import persistence.WriterHelper;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WriterHelperTest {

    @Test
    public void writerHelperTest(){
        WriterHelper<Article> wh = new WriterHelper<>();

        List<Article> art = new ArrayList<>();
        Article a = new Article(1, "test name", "test description", ArticleCategory.Office, ArticleStatus.Active, 25, 50, null, LocalDateTime.now(), LocalDateTime.now());
        art.add(a);

        SerializeBuilder sb = new SerializeBuilder();
        sb.addUserSerializer();
        sb.addLocalDateTimeSerializer();
        Gson gson = sb.getGson();

        String jsonString = gson.toJson(art);


        File f = new File("test.json");
        try {
            f.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        wh.writeToFileSerializer("src/test/java/persistencetests/test.json", art, gson);

        List<Article> article = null;

        Gson gson2 = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new JsonDeserializer<LocalDateTime>() {
            @Override
            public LocalDateTime deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

                return LocalDateTime.parse(json.getAsString());
            }
        }).create();


        try {
            Reader reader = Files.newBufferedReader(Path.of("src/test/java/persistencetests/test.json"));
            article = List.of(gson2.fromJson(reader, Article[].class));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        f.delete();

        assertEquals(jsonString, gson.toJson(article));
    }
}
