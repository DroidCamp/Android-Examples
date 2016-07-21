package appcamp.hemang.listviewapp;

/**
 * Created by Hemang on 20/07/16.
 */
public class CarModel {
    String name;
    String id;

    public CarModel(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
