package persistence;

import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import model.customer.Customer;

import model.customer.CustomerContact;
import persistence.pojos.CustomerJSON;

public final class CustomersDAO implements IPersistence<Customer> {
    private static CustomersDAO instance = null;
    private  String customersFile = "src/main/resources/customers.json";
    private Map<Long, Customer> customers = new HashMap<>();
    private Gson gson = new Gson();

    // refactor. Use strategy pattern maybe?
    private long nextFreeId=0;

    private CustomersDAO() {
        readFile();
    }

    // so that testing can be done on dummy data
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
            if(customers.size() > 0) {
                nextFreeId= Collections.max(customers.keySet()) + 1;
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


    @Override
    public void save(List<Customer> list) {
        SerializeBuilder sb = new SerializeBuilder();
        WriterHelper<Customer> wh = new WriterHelper<>();
        wh.WriteToFileSerializer(customersFile, list, sb.getGson());
    }

    @Override
    public List<Customer> getAll() {
        return new ArrayList<>(this.customers.values());
    }

    @Override
    public Map<Long, Customer> getAllMap() {
        return this.customers;
    }

    @Override
    public long getNextId() {
        if (customers.isEmpty()){
            return 1;
        } else {
            Set<Long> keys = customers.keySet();
            long maxKeyVal = Collections.max(keys);
            return maxKeyVal + 1;
        }
    }

    @Override
    public Customer findById(long id) {
        return this.customers.get(id);
    }

}
