package ordertests;

import model.WMS;
import model.customer.Customer;
import model.order.Order;
import model.order.OrderStatus;
import model.site.Site;
import model.user.Role;
import model.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.order.Orders;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


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

    @Test
    public void testGetOrdersByCustomer(){
        Orders orders = wms.getOrders();
    }

    @Test
    public void testCheckIfExist(){
        Orders orders = wms.getOrders();
        assertTrue(orders.checkIfExist(1L));
    }

    @Test
    public void testFindById(){
        Orders orders = wms.getOrders();
        Order order = wms.getOrders().getInList().stream().findFirst().get();
        assertEquals(orders.findById(1L),order);
    }

    @Test
    public void testAddOrder(){
        Orders orders = wms.getOrders();
        int sizeBefore = orders.getInList().size();


        Site site = new Site(9, "test site", "test site address", 500, null, null);
        Customer cust1 = new Customer(new ArrayList<>(), null, null, 9, 24, "testName");
        User user = new User(9, "testuser", "1234", "test name", true, Role.getAdmin());
        Order order1 = new Order(user, orders.getNextOrderNumber(), cust1, OrderStatus.ACTIVE, true, LocalDateTime.now(), LocalDateTime.now(), null, site);
        orders.addOrder(order1);
        int sizeAfter = orders.getInList().size();


        assertTrue(sizeBefore == sizeAfter - 1);
    }

    @Test
    public void testGetNextOrderNumber(){
        Orders orders = wms.getOrders();
        assertEquals(orders.getNextOrderNumber(),1001);
    }

}
