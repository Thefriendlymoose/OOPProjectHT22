package persistance;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

import com.google.gson.Gson;
import model.article.Article;
import persistance.pojos.ArticleJSON;

public final class ArticlesDAO implements IPersistenceNew<Article> {
    private static ArticlesDAO instance;
    private final String articlesFile="src/main/resources/articles.json";
    private Map<Long, Article> articles = new HashMap<>();
    private Gson gson = new Gson();
    private long nextFreeId = 0;

    private ArticlesDAO() {
        try {
            Reader reader = Files.newBufferedReader(Path.of(articlesFile));
            List<ArticleJSON> articleJSONS = Arrays.asList(gson.fromJson(reader, ArticleJSON[].class));
            for(ArticleJSON aj : articleJSONS) {
                Article article = new Article(aj.getArticleId(), aj.getArticleName(), aj.getDescription(),
                        aj.getCategory(), aj.getStatus(), aj.getCost(), aj.getSellPrice());

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

    @Override
    public void save(Article article) {

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
