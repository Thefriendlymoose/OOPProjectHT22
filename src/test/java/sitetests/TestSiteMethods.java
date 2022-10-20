package sitetests;

import model.article.Article;
import model.article.ArticleCategory;
import model.article.ArticleStatus;
import model.site.Site;
import model.site.SiteArticle;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.ArrayList;
import java.util.List;

public class TestSiteMethods {
    Article article1 = new Article(1,"Name 1", "Desc 1", ArticleCategory.Beds, ArticleStatus.Active,1,2,null,null,null);
    Article article2 = new Article(2,"Name 2", "Desc 2", ArticleCategory.Beds, ArticleStatus.Active,2,3,null,null,null);
    Article article3 = new Article(3,"Name 3", "Desc 3", ArticleCategory.Beds, ArticleStatus.Active,3,4,null,null,null);

    SiteArticle siteArticle1 = new SiteArticle(article1,1);
    SiteArticle siteArticle2 = new SiteArticle(article2,2);
    SiteArticle siteArticle3 = new SiteArticle(article3,99999);



    @Test
    public void totalAmountItems(){
        List<SiteArticle> siteArticles = new ArrayList<>();
        siteArticles.add(siteArticle1);
        siteArticles.add(siteArticle2);

        Site site1 = new Site(1,"Name 1","Adress 1",5,siteArticles,null);

        int sum = 1+2;
        assertEquals(site1.getTotalAmountItems(),sum);
    }

    @Test
    public void isOverCapacity(){
        List<SiteArticle> siteArticles = new ArrayList<>();
        siteArticles.add(siteArticle3);

        Site site1 = new Site(1,"Name 1","Adress 1",5,siteArticles,null);

        assertTrue(site1.isOverCapacity(siteArticle3,99999));
    }

    @Test
    public void checkIfOverCapacity(){
        List<SiteArticle> siteArticles = new ArrayList<>();
        siteArticles.add(siteArticle1);


        Site site1 = new Site(1,"Name 1","Adress 1",5,siteArticles,null);

        assertTrue(site1.checkIfOverCapacity(6));
    }

}
