package ordertests;

import model.article.Article;
import model.article.ArticleCategory;
import model.article.ArticleStatus;
import model.order.OrderRow;
import model.order.Order;
import model.order.OrderStatus;
import model.site.Site;
import model.site.SiteArticle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.ArrayList;
import java.util.List;

public class TestOrderMethods {

    Article article1 = new Article(1,"Name 1", "Desc 1", ArticleCategory.Beds, ArticleStatus.Active,1,2,null,null,null);
    Article article2 = new Article(2,"Name 2", "Desc 2", ArticleCategory.Beds, ArticleStatus.Active,2,3,null,null,null);
    Article article3 = new Article(3,"Name 3", "Desc 3", ArticleCategory.Beds, ArticleStatus.Active,3,4,null,null,null);
    OrderRow orderRow1 = new OrderRow(article1,1);
    OrderRow orderRow2 = new OrderRow(article2,2);
    OrderRow orderRow3 = new OrderRow(article1,3);

    SiteArticle siteArticle1 = new SiteArticle(article1,1);
    SiteArticle siteArticle2 = new SiteArticle(article2,2);

    Order order1 = new Order(null,11,null,OrderStatus.ACTIVE,true,null,null,null,null);
    List<SiteArticle> siteArticles = new ArrayList<>();
    Site site1 = new Site(1,"Name 1","Adress 1",5,siteArticles,null);



    @Test void totalRevenue(){
        List<OrderRow> dummyOrderRows = new ArrayList<>();
        dummyOrderRows.add(orderRow1);
        dummyOrderRows.add(orderRow2);

//        Order order1 = new Order(null,1,null, OrderStatus.ACTIVE,true,null,null,dummyOrderRows,null);

        assertEquals(order1.getTotalRevenue(),8);
    }

    @Test
    public void totalAmount(){
        List<OrderRow> dummyOrderRows = new ArrayList<>();
        dummyOrderRows.add(orderRow1);
        dummyOrderRows.add(orderRow2);

        Order order1 = new Order(null,1,null, OrderStatus.ACTIVE,true,null,null,dummyOrderRows,null);

        assertEquals(order1.getTotalAmount(), order1.getOrderRows().get(0).getAmount() + order1.getOrderRows().get(1).getAmount());
        assertEquals(order1.getTotalAmount(), 1 + 2);
    }



    @Test
    public void addingOrderRow(){
        List<OrderRow> dummyOrderRows = new ArrayList<>();
        dummyOrderRows.add(orderRow1);



        Order order1 = new Order(null,1,null, OrderStatus.ACTIVE,true,null,null,dummyOrderRows,null);

        int amountBefore = order1.getOrderRows().get(0).getAmount();
        order1.addOrderRow(siteArticle1,1);
        int amountAfter = order1.getOrderRows().get(0).getAmount();

        assertEquals(amountBefore + 1, amountAfter);

    }

    @Test
    public void reducingOrderRow1(){

//        Test to satisfy
//        if (or.getAmount() == amount){
//            orderRows.remove(or);
//            return site.addSiteArticle(art, amount);

        List<OrderRow> dummyOrderRows = new ArrayList<>();
        dummyOrderRows.add(orderRow1);
        dummyOrderRows.add(orderRow2);


        site1.addSiteArticle(article1,1);
        Order order1 = new Order(null,1,null, OrderStatus.ACTIVE,true,null,null,dummyOrderRows,site1);


        assertTrue(order1.reduceOrderRow(orderRow1,1));

    }

    @Test
    public void reducingOrderRow2(){
//        Test to satisfy
//        if (or.getAmount() < amount || amount < 0){
//            return false;

        List<OrderRow> dummyOrderRows = new ArrayList<>();
        dummyOrderRows.add(orderRow1);
        dummyOrderRows.add(orderRow2);


        site1.addSiteArticle(article1,1);
        Order order1 = new Order(null,1,null, OrderStatus.ACTIVE,true,null,null,dummyOrderRows,site1);


        assert(!order1.reduceOrderRow(orderRow1,2));

    }

    @Test
    public void reducingOrderRow3(){

//        Test to satisfy
//        if (site.checkIfOverCapacity(amount))
//        return false;

        List<OrderRow> dummyOrderRows = new ArrayList<>();
        dummyOrderRows.add(orderRow1);
        dummyOrderRows.add(orderRow2);


        site1.addSiteArticle(article1,1);
        Order order1 = new Order(null,1,null, OrderStatus.ACTIVE,true,null,null,dummyOrderRows,site1);


        assert(!order1.reduceOrderRow(orderRow3,999999999));
}

//    @Test
//    public void reducingOrderRow4(){
//        List<OrderRow> dummyOrderRows = new ArrayList<>();
//        dummyOrderRows.add(orderRow1);
//        dummyOrderRows.add(orderRow2);
//
////      else {
////            or.reduceAmount(amount);
////            return site.addSiteArticle(art, amount);
////           }
//
//        Order order1 = new Order(null,1,null, OrderStatus.ACTIVE,true,null,null,dummyOrderRows,null);
//    }

}
