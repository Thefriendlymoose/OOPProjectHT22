package ordertests;

import model.WMS;
import model.order.OrderStatus;
import model.site.Site;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.order.Orders;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestOrders {

    private WMS wms;

    @BeforeEach
    public void init(){
        TestWMS t = new TestWMS();
        this.wms = t.getWMS();
    }

    @Test
    public void testGetOrdersBySite(){
        Orders orders = wms.getOrders();

        Site site = wms.getSites().getInList().stream().findFirst().get();

        int size = orders.getOrdersBySite(site).size();
        assertEquals(size,2);
    }

    @Test
    public void testGetCostPerOrderStatus(){
        Orders orders = wms.getOrders();
        float totalCostActiveStatus = orders.getCostPerOrderStatus(OrderStatus.ACTIVE);
        assertEquals(totalCostActiveStatus,50.0);
    }

    @Test
    public void testRevenuePerOrderStatus(){
        Orders orders = wms.getOrders();
        float totalRevenueActiveStatus = orders.getRevenuePerOrderStatus(OrderStatus.ACTIVE);
        assertEquals(totalRevenueActiveStatus,100.0);
    }

    @Test
    public void testGetProfitPerOrderStatus(){
        Orders orders = wms.getOrders();
        float totalProfitActiveStatus = orders.getProfitPerOrderStatus(OrderStatus.ACTIVE);

        System.out.println(totalProfitActiveStatus);
        assertEquals(totalProfitActiveStatus,50.0);
    }



}
