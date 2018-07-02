package accountCreate;

import beans.Customer;
import db.DBHandler;
import org.apache.commons.validator.routines.EmailValidator;
import org.json.JSONObject;

public class SignupHandler {

    public static JSONObject signUp(Customer customer) {
        JSONObject response = new JSONObject();

        if (!isValid(customer.getEmail())){
            response.put("error", true);
            response.put("error_message", "Invalid email");
            return response;
        }

        if (!isUnique(customer.getEmail())){
            response.put("error", true);
            response.put("error_message", "Account already exists");
            return response;
        }

        DBHandler dbHandler = new DBHandler();
        dbHandler.createAccount(customer);
        response.put("error", false);
        return response;
    }

    //This validation should be done on the client end
    //This should be removed
    private static boolean isValid(String email) {
        return EmailValidator.getInstance().isValid(email);
    }

    private static boolean isUnique(String email){
        return new DBHandler().getCustomer(email) == null;
    }

}
