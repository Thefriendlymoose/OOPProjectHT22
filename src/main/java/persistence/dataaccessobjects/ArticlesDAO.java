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


public final class ArticlesDAO implements IPersistence<Article> {
    private static ArticlesDAO instance;
    private final String articlesFile = "src/main/resources/articles.json";
    private Map<Long, Article> articles = new HashMap<>();
    private Gson gson;
    private long nextFreeId = 0;

    private Map<Long, User> users = UserDAO.getInstance().getAllMap();

    /**
     * Inner class for json export of formatted datetime values
     * Source <a href="https://www.javaguides.net/2019/11/gson-localdatetime-localdate.html">Java Guides</a>
     */

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
            if(articles.size() > 0) {
                nextFreeId = Collections.max(articles.keySet()) + 1;
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
     * Save article data to a json file
     * @param
     */
    @Override
    public void save(List<Article> list) {
        SerializeBuilder sb = new SerializeBuilder();
        sb.addUserSerializer();
        sb.addLocalDateTimeSerializer();
        WriterHelper<Article> wh = new WriterHelper<>();
        wh.WriteToFileSerializer(articlesFile, list, sb.getGson());
    }

    @Override
    public List<Article> getAll() {
        return new ArrayList<>(this.articles.values());
    }

    @Override
    public Map<Long, Article> getAllMap() {
        return this.articles;
    }

    @Override
    public long getNextId() {
        return this.nextFreeId;
    }

    @Override
    public Article findById(long id) {
        return this.articles.get(id);
    }

}
