package ordertests;

import model.WMS;
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
        System.out.println(orders.getOrdersBySite(site).size());

        int size = orders.getOrdersBySite(site).size();
//        assertEquals(size,);
    }

}
