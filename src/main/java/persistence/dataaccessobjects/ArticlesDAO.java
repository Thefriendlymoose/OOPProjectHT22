package persistence.dataaccessobjects;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.*;

import com.google.gson.*;
import model.user.User;
import model.article.Article;
import persistence.IPersistence;
import persistence.SerializeBuilder;
import persistence.WriterHelper;
import persistence.pojos.ArticleJSON;

/**
 * Data Access Object which handles loading and saving articles to/from JSON
 */
public final class ArticlesDAO implements IPersistence<Article> {
    private static ArticlesDAO instance;
    private final String articlesFile = "src/main/resources/articles.json";
    private Map<Long, Article> articles = new HashMap<>();
    private Gson gson;
    private Map<Long, User> users = UserDAO.getInstance().getAllMap();

    private ArticlesDAO() {
        gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new JsonDeserializer<LocalDateTime>() {
            @Override
            public LocalDateTime deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

                return LocalDateTime.parse(json.getAsString());
            }
        }).create();


        try {
            Reader reader = Files.newBufferedReader(Path.of(articlesFile));
            List<ArticleJSON> articleJSONS = Arrays.asList(gson.fromJson(reader, ArticleJSON[].class));
            for(ArticleJSON aj : articleJSONS) {
                Article article = new Article(aj.getArticleId(), aj.getArticleName(), aj.getDescription(),
                        aj.getCategory(), aj.getStatus(), aj.getCost(), aj.getSellPrice(), users.get(aj.getCreatedBy()), aj.getCreatedOn(), aj.getLastEdited());

                articles.put(article.getArticleId(), article);
            }

        } catch (Exception e){
            System.out.println(e);
        }
    }

    /**
     * Singleton
     * @return ArticlesDAO
     */
    public static ArticlesDAO getInstance(){
        if(instance == null) {
            instance = new ArticlesDAO();
        }
        return instance;
    }

    /**
     * Serializes a list of articles into JSON
     * @param
     */
    @Override
    public void save(List<Article> list) {
        SerializeBuilder sb = new SerializeBuilder();
        sb.addUserSerializer();
        sb.addLocalDateTimeSerializer();
        WriterHelper<Article> wh = new WriterHelper<>();
        wh.writeToFileSerializer(articlesFile, list, sb.getGson());
    }

    @Override
    public Map<Long, Article> getAllMap() {
        return this.articles;
    }


}
