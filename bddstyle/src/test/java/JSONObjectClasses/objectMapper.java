package JSONObjectClasses;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import POJOClasses.registerUser;

public class objectMapper {

    registerUser users = new registerUser();
    ObjectMapper mapper = new ObjectMapper();

    @Test
    public void serialization() throws JsonProcessingException {

        users.setEmail("eve.holt@reqres.in");
        users.setPassword("pistol");

        String usersJson = mapper.writeValueAsString(users);
        System.out.println(usersJson);

        String usersPrettyJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(users);
        System.out.println(usersPrettyJson);

    }

    @Test
    public void de_serialization() throws JsonProcessingException {

        users.setEmail("eve.holt@reqres.in");
        users.setPassword("pistol");

        String usersJson = mapper.writeValueAsString(users);
        System.out.println(usersJson);

        registerUser users2 = mapper.readValue(usersJson, registerUser.class);
        System.out.println(users2.getEmail());
        System.out.println(users2.getPassword());

    }

}
