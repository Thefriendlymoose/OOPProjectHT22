package articleTests;

import model.article.Article;
import model.article.Articles;
import model.article.ArticlesFacade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestAddArticle {

    private Articles articles;

/*    @BeforeEach
    void initAll(){
        HashMap<Long, Article> arts = new HashMap<>();

        arts.put(1L, new Article(1, null, null, null, null, 1, 1, null, null, null));

        this.articles = new Articles(arts);
    }*/

    @Test
    public void addArticle(){
        ArticlesFacade fac = new ArticlesFacade();
        int size = fac.getAll().size();
        fac.saveArticle(new Article(100, "test", "test", null, null, 1,1,null,null,null));
        int size2 = fac.getAll().size();
        assertTrue(size+1 == size2);
    }
}
