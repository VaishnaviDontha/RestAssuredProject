package JSONObjectClasses;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import POJOClasses.registerUser;

public class objectMapperJSONArray {

    @Test
    public void serializationViaJSONArray() throws JsonProcessingException {

        registerUser user1 = new registerUser();
        user1.setEmail("eve.holt@reqres.in");
        user1.setPassword("pistol");

        registerUser user2 = new registerUser();
        user2.setEmail("sydney@fife.in");
        user2.setPassword("pis");

        List<registerUser> registerUsersList = new ArrayList<registerUser>();
        registerUsersList.add(user1);
        registerUsersList.add(user2);

        ObjectMapper mapper = new ObjectMapper();
        String registerUserJson = mapper.writeValueAsString(registerUsersList);
        System.out.println(registerUserJson);

        String registerUserListPrettyPrint = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(registerUserJson);
        System.out.println(registerUserListPrettyPrint);
        
    }

    @Test
    public void de_serializationViaJSONArray() throws JsonProcessingException {

        registerUser user1 = new registerUser();
        user1.setEmail("eve.holt@reqres.in");
        user1.setPassword("pistol");

        registerUser user2 = new registerUser();
        user2.setEmail("sydney@fife.in");
        user2.setPassword("pis");

        List<registerUser> registerUsersList = new ArrayList<registerUser>();
        registerUsersList.add(user1);
        registerUsersList.add(user2);

        ObjectMapper mapper = new ObjectMapper();
        String registerUserJson = mapper.writeValueAsString(registerUsersList);
        System.out.println(registerUserJson);
        
        List<registerUser> allRegisterUsers = mapper.readValue(registerUserJson, new TypeReference<List<registerUser>>() {
            
        });

        for (registerUser registerUser : allRegisterUsers) {

            System.out.println(registerUser.getEmail());
            System.out.println(registerUser.getPassword());
            
        }

        
    }

}
