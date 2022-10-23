package sitetests;

import model.WMS;
import model.article.Article;
import model.site.Site;
import model.site.SiteArticle;
import model.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.internal.connection.tlschannel.util.Util.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SiteTests {

    private Site site;
    private WMS wms;

    @BeforeEach
    public void init(){
        TestWMS test = new TestWMS();
        this.wms = test.getWMS();

        List<Article> arts = wms.getArticles().getInList();
        List<User> users = wms.getUsers().getAllUsers();

        List<SiteArticle> sas = new ArrayList<>();

        int am = 5;
        for (Article art : arts){
            sas.add(new SiteArticle(art, am));
            am+= 3;
        }

        this.site = new Site(1, "test site", "test site address", 500, sas, users);
    }

    @Test
    public void totalAmountItems(){
        int totalBefore = site.getTotalAmountItems();
        Article art = wms.getArticles().findById(1);
        site.addSiteArticle(art, 25);
        int totalAfter = site.getTotalAmountItems();
        assertEquals(totalBefore + 25, totalAfter);
    }

    @Test
    public void overCapacity(){
        int totalBefore = site.getTotalAmountItems();
        int max = site.getMaxCapacity();
        Article art = wms.getArticles().findById(1);
        boolean b = site.addSiteArticle(art, max);
        int totalAfter = site.getTotalAmountItems();
        assertEquals(totalBefore, totalAfter);
        assertTrue(!b);
    }

    @Test
    public void addEmployee(){
        User user = new User(100, "1", "1", "test", true, null);
        int beforeAdd = site.getSiteUsers().size();
        site.addEmployee(user);
        int afterAdd = site.getSiteUsers().size();
        assertEquals(beforeAdd + 1, afterAdd);
    }

    @Test
    public void addSameEmployee(){
        int beforeAdd = site.getSiteUsers().size();
        User user = site.getSiteUsers().stream().findFirst().get();
        boolean b = site.addEmployee(user);
        int afterAdd = site.getSiteUsers().size();
        assertEquals(beforeAdd, afterAdd);
        assertTrue(!b);
    }

    @Test
    public void addSiteArticle(){
        SiteArticle sa = site.getSiteArticles().stream().findFirst().get();
        int amount1 = sa.getAmount();
        boolean b = site.addSiteArticle(sa.getArticle(), 25);
        int amount2 = sa.getAmount();
        assertEquals(amount1 + 25, amount2);
        assertTrue(b);
    }

    @Test
    public void findSiteArticle(){
        SiteArticle sa = site.getSiteArticles().stream().findFirst().get();
        SiteArticle sa2 = site.findSiteArticle(sa.getArticle());
        assertEquals(sa, sa2);
    }

    @Test
    public void editSiteArticle(){
        SiteArticle sa = site.getSiteArticles().stream().findFirst().get();
        int setAmount = 25;
        site.editSiteArticle(sa, setAmount);
        assertEquals(sa.getAmount(), setAmount);
    }

    @Test
    public void getSiteArticleOverZero(){
        SiteArticle sa = site.getSiteArticles().stream().findFirst().get();
        int amountBefore = site.getSiteArticleOverZero().size();
        sa.setAmount(0);
        int amountAfter = site.getSiteArticleOverZero().size();
        assertEquals(amountBefore - 1, amountAfter);
    }
}
