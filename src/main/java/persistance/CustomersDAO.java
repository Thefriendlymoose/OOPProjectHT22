package persistance;

import com.google.gson.Gson;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import model.customer.Customer;

import model.customer.ICustomerContact;

public final class CustomersDAO implements IPersistenceNew<Customer> {
    private static CustomersDAO instance=null;
    private final String customersFile="src/main/resources/customers.json";
    private Map<Long, Customer> customers=new HashMap<>();
    private Gson gson = new Gson();
    private long nextFreeId=0;

    private CustomersDAO() {
        try {
            Reader reader = Files.newBufferedReader(Path.of(customersFile));
            List<CustomerJSON> customerJSONS = Arrays.asList(gson.fromJson(reader, CustomerJSON[].class));
            for(CustomerJSON cj : customerJSONS) {
                List<ICustomerContact> customerContacts = Arrays.asList(cj.customerContacts);


                Customer customer =new Customer(customerContacts, cj.billingAddress, cj.shippingAddress,
                        cj.customerId, cj.companyOrgNumber, cj.companyName);
                customers.put( Long.valueOf(cj.customerId), customer);
            }
            if(customers.size()>0) {
                nextFreeId= Collections.max(customers.keySet());
            }
            nextFreeId++;
        } catch (Exception e){
            System.out.println(e);
        }
    }

    /**
     * Singleton
     * @return CustomersDAO
     */
    public static CustomersDAO getInstance(){
        if(instance == null) {
            instance = new CustomersDAO();
        }
        return instance;
    }

    @Override
    public void Save(Customer customer) {
        Long key = Long.valueOf(customer.getCustomerID());
        if(!customers.containsKey(key)) {
            customers.put(key, customer);
        }
    }

    @Override
    public List<Customer> getAll() {
        List<Customer> customersList = new ArrayList<>(this.customers.values());
        return customersList;
    }

    @Override
    public Map<Long, Customer> getAllMap() {
        return this.customers;
    }

    @Override
    public long getNextId() {
        return this.nextFreeId;
    }

    @Override
    public Customer findById(long id) {
        return this.customers.get(id);
    }

}
