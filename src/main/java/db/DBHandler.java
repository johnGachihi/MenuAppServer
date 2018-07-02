package db;

import beans.Customer;
import com.mongodb.MongoClient;
import org.json.JSONObject;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import java.util.List;

public class DBHandler {

    private static Datastore datastore;

    public DBHandler(){
        if (datastore == null) {
            datastore = new Morphia().createDatastore(
                    new MongoClient(), "MenuAppDB");
        }
    }

    public Customer getCustomer(String email) {
        List<Customer> customer = datastore.createQuery(Customer.class)
                .filter("email =", email)
                .asList();

        if (customer.isEmpty())
            return null;

        return customer.get(0);
    }

    public void createAccount(Customer customer){
        datastore.save(customer);
    }
}
