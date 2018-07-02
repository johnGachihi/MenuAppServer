package authentication;

import beans.Customer;
import db.DBHandler;
import org.json.JSONObject;
import org.mongodb.morphia.Morphia;

public class Authenticator {

    public static JSONObject verifyCredentials(String email, String password) {
        DBHandler dbHandler = new DBHandler();
        Customer customer = dbHandler.getCustomer(email);

        JSONObject response = new JSONObject();
        if(customer == null) {
            response.put("error_message", "Account does not exist");
            return response.put("valid", false);
        } else {
            if(customer.getPassword().equals(password)){
                response.put("customer", new JSONObject(customer));
                return response.put("valid", true);
            } else {
                response.put("error_message", "Incorrect credentials");
                return response.put("valid", false);
            }
        }
    }
}
