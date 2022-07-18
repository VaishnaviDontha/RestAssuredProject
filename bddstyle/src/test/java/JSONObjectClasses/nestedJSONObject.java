package JSONObjectClasses;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import POJOClasses.category;
import POJOClasses.data;
import POJOClasses.nestedJSONObjectPOJO;

public class nestedJSONObject {

    @Test
    public void nestedPOJO() throws IOException {

        nestedJSONObjectPOJO pojo = new nestedJSONObjectPOJO();

        category category1 = new category();
        category1.setCategoryID("12");
        category1.setCategoryName("TestCategory1");

        List<category> categoryList = new ArrayList<category>();
        categoryList.add(category1);

        data data1 = new data();
        data1.setFirstName("TestFName");
        data1.setLastName("TestLName");
        data1.setMainID("01");
        data1.setCategories(categoryList);

        List<data> dataList = new ArrayList<data>();
        dataList.add(data1);


        pojo.setData(dataList);
        pojo.setMessages("TestMessage1");
        pojo.setSuccess("Success1");
        
        ObjectMapper mapper = new ObjectMapper();
        String nestedJSONPayload = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(pojo);

        System.out.println(nestedJSONPayload);

        // String userDir = System.getProperty("user.dir");
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File("/Users/harishkasam/Documents/Vaishnavi/Practise-Repo/RestAssured/bddstyle/src/test/resources/NestedJSONPayload.json"), pojo);
    }

}
