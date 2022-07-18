package POJOClasses;

import java.util.List;

public class nestedJSONObjectPOJO {

    private List<data> data;
    private String messages;
    private String success;

    public void nestedJSONObjectPOJO() {

    }

    public List<data> getData() {
        return data;
    }

    public void setData(List<data> data) {
        this.data = data;
    }

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

}
