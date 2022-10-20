package orderTests;

import model.article.Article;
import model.article.ArticleCategory;
import model.article.ArticleStatus;
import model.order.OrderRow;
import model.order.Order;
import model.order.OrderStatus;
import model.site.SiteArticle;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeEach;
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
    Order order1 = new Order(null,11,null,OrderStatus.ACTIVE,true,null,null,null,null);


    @BeforeEach
    public void initEach(){
        List<OrderRow> dummyOrderRows = new ArrayList<>();
        Article article1 = new Article(1,"Name 1", "Desc 1", ArticleCategory.Beds, ArticleStatus.Active,1,2,null,null,null);
        Article article2 = new Article(2,"Name 2", "Desc 2", ArticleCategory.Lighting, ArticleStatus.Discontinued,2,3,null,null,null);
        Article article3 = new Article(3,"Name 3", "Desc 3", ArticleCategory.Electronics, ArticleStatus.Limited,3,4,null,null,null);


        dummyOrderRows.add(new OrderRow(article1,1));
        dummyOrderRows.add(new OrderRow(article2,2));
        dummyOrderRows.add(new OrderRow(article3,3));

//        List<Order> dummyOrders = new ArrayList<>();
//        Order order1 = new Order(null,1,null, OrderStatus.ACTIVE,true,null,null,dummyOrderRows,null);
        System.out.println("BeforeEach initEach() method called");
    }


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

        SiteArticle siteArticle1 = new SiteArticle(article1,1);

        Order order1 = new Order(null,1,null, OrderStatus.ACTIVE,true,null,null,dummyOrderRows,null);

        int amountBefore = order1.getOrderRows().get(0).getAmount();
        order1.addOrderRow(siteArticle1,1);
        int amountAfter = order1.getOrderRows().get(0).getAmount();

        assertEquals(amountBefore + 1, amountAfter);

    }

    @Test
    public void reducingOrderRow1(){
        List<OrderRow> dummyOrderRows = new ArrayList<>();
        dummyOrderRows.add(orderRow1);
        dummyOrderRows.add(orderRow2);

//        if (or.getAmount() == amount){
//            orderRows.remove(or);
//            return site.addSiteArticle(art, amount);

        Order order1 = new Order(null,1,null, OrderStatus.ACTIVE,true,null,null,dummyOrderRows,null);

    }

    @Test
    public void reducingOrderRow2(){
        List<OrderRow> dummyOrderRows = new ArrayList<>();
        dummyOrderRows.add(orderRow1);
        dummyOrderRows.add(orderRow2);

//        if (or.getAmount() < amount || amount < 0){
//            return false;

        Order order1 = new Order(null,1,null, OrderStatus.ACTIVE,true,null,null,dummyOrderRows,null);

    }

    @Test
    public void reducingOrderRow3(){
        List<OrderRow> dummyOrderRows = new ArrayList<>();
        dummyOrderRows.add(orderRow1);
        dummyOrderRows.add(orderRow2);

//       if (site.checkIfOverCapacity(amount)) {
//        return false;

        Order order1 = new Order(null,1,null, OrderStatus.ACTIVE,true,null,null,dummyOrderRows,null);
    }

    @Test
    public void reducingOrderRow4(){
        List<OrderRow> dummyOrderRows = new ArrayList<>();
        dummyOrderRows.add(orderRow1);
        dummyOrderRows.add(orderRow2);

//      else {
//            or.reduceAmount(amount);
//            return site.addSiteArticle(art, amount);
//           }

        Order order1 = new Order(null,1,null, OrderStatus.ACTIVE,true,null,null,dummyOrderRows,null);
    }

}
