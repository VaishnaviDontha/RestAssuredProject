package POJOClasses;

import java.util.List;

public class data {

    private String MainID;
    private String firstName;
    private String lastName;
    private List<category> categories;

    public void data() {

    }

    public String getMainID() {
        return MainID;
    }

    public void setMainID(String mainID) {
        MainID = mainID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<category> getCategories() {
        return categories;
    }

    public void setCategories(List<category> categories) {
        this.categories = categories;
    }

}
