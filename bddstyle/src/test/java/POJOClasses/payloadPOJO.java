package POJOClasses;

import java.util.List;

public class payloadPOJO {

    private String userId;
    private List<collectionIsbn> collectionOfIsbns;

    public void authPOJO() {

    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<collectionIsbn> getCollectionOfIsbns() {
        return collectionOfIsbns;
    }

    public void setCollectionOfIsbns(List<collectionIsbn> collectionOfIsbns) {
        this.collectionOfIsbns = collectionOfIsbns;
    }

}
