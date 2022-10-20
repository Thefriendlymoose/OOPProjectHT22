package articleTests;


import model.WMS;
import model.article.Article;
import model.article.ArticleCategory;
import model.article.ArticleStatus;
import model.article.Articles;


import model.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestAddArticle {

    private WMS wms;
    @BeforeEach
    public void init(){
        TestWMS t = new TestWMS();
        this.wms = t.getWMS();
    }

    @Test
    public void addArticle(){
        Articles articles = wms.getArticles();
        int sizeBeforeAdd = articles.getInList().size();
        wms.addArticle(new Article(articles.getNextId(), "test name", "test description", ArticleCategory.Kitchen, ArticleStatus.Active, 25, 50, null, LocalDateTime.now(), LocalDateTime.now()));
        int sizeAfterAdd = articles.getInList().size();
        assertEquals(sizeAfterAdd - 1, sizeBeforeAdd);
    }

    @Test
    public void getByCategory(){
        Articles articles = wms.getArticles();
        List<Article> byCat = articles.getArticlesByCategory(ArticleCategory.Kitchen);
        System.out.println(byCat);
        assertEquals(2, byCat.size());
    }

    @Test
    public void getByStatus() {
        Articles articles = wms.getArticles();
        List<Article> byStat = articles.getArticlesByStatus(ArticleStatus.Limited);
        assertEquals(1, byStat.size());
    }

    @Test
    public void findById(){
        Articles articles = wms.getArticles();
        Long id = articles.getNextId();
        Article newArt = new Article(articles.getNextId(), "test name", "test description", ArticleCategory.Kitchen, ArticleStatus.Active, 25, 50, null, LocalDateTime.now(), LocalDateTime.now());
        wms.addArticle(newArt);
        Article findArt = articles.findById(id);
        assertEquals(newArt, findArt);
    }

    @Test
    public void nextId(){
        Articles articles = wms.getArticles();
        Long id = articles.getNextId();
        Article newArt = new Article(id, "test name", "test description", ArticleCategory.Kitchen, ArticleStatus.Active, 25, 50, null, LocalDateTime.now(), LocalDateTime.now());
        wms.addArticle(newArt);
        assertEquals(id + 1, articles.getNextId());
    }
}
