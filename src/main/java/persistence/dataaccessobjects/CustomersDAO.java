package persistence.dataaccessobjects;

import com.google.gson.Gson;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import model.customer.Customer;

import model.customer.CustomerContact;
import persistence.IPersistence;
import persistence.SerializeBuilder;
import persistence.WriterHelper;
import persistence.pojos.CustomerJSON;

/**
 * Data Access Object which handles loading/saving Customer data from/to JSON
 */
public final class CustomersDAO implements IPersistence<Customer> {
    private static CustomersDAO instance = null;
    private  String customersFile = "src/main/resources/customers.json";
    private Map<Long, Customer> customers = new HashMap<>();
    private Gson gson = new Gson();


    private CustomersDAO() {
        readFile();
    }


    public void readFile(){
        try {
            Reader reader = Files.newBufferedReader(Path.of(customersFile));
            List<CustomerJSON> customerJSONS = Arrays.asList(gson.fromJson(reader, CustomerJSON[].class));
            for(CustomerJSON cj : customerJSONS) {
                List<CustomerContact> customerContacts = Arrays.asList(cj.getCustomerContacts());

                Customer customer = new Customer(customerContacts, cj.getBillingAddress(), cj.getShippingAddress(),
                        cj.getCustomerId(), cj.getCompanyOrgNumber(), cj.getCompanyName());
                customers.put(cj.getCustomerId(), customer);
            }
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

    public void setCustomersFile(String filePath){
        customersFile = filePath;
    }


    public void save(Customer customer) {
        Long key = customer.getCustomerID();
        if(!customers.containsKey(key)) {
            customers.put(key, customer);
        }
    }

    /**
     * Serializes a list of customers into JSON
     * @param list
     */
    @Override
    public void save(List<Customer> list) {
        SerializeBuilder sb = new SerializeBuilder();
        WriterHelper<Customer> wh = new WriterHelper<>();
        wh.writeToFileSerializer(customersFile, list, sb.getGson());
    }


    @Override
    public Map<Long, Customer> getAllMap() {
        return this.customers;
    }


}
