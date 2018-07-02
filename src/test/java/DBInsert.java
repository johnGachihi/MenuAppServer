import beans.Customer;
import com.mongodb.MongoClient;
import org.json.JSONObject;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import java.util.ArrayList;

public class DBInsert {

    public static void main(String[] args) {
        /*Morphia morphia = new Morphia();
        morphia.mapPackage("beans");

        final Datastore datastore = morphia.createDatastore(new MongoClient
                ("localhost",27017), "MenuAppDB");

        ArrayList customers = new ArrayList();
        customers.add(new Customer("john@gmail.com", "12345"));
        customers.add(new Customer("stephen@mburu.com", "12345"));
        datastore.save(customers);*/

        JSONObject user = new JSONObject(new Customer("ble@g.com", "1111"));
        JSONObject response = new JSONObject().put("user", user).put("message", "Invalid credentials").put("valid", true);

        String a = response.toString();
        System.out.println(a);
    }

}
