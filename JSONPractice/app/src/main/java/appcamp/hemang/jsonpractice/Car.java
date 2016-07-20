package appcamp.hemang.jsonpractice;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Hemang on 05/07/16.
 */
public class Car {
    private String name;
    private boolean wheelie;

    static final String JSON_NAME = "NAME";
    static final String JSON_WHEEL = "WHEELIE";

    public Car(JSONObject jo) throws JSONException {

        name = (String) jo.get(JSON_NAME);
        wheelie = (boolean) jo.get(JSON_WHEEL);


    }
    public JSONObject makeItJSON() throws JSONException {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put(JSON_NAME, name);
        jsonObject.put(JSON_WHEEL, wheelie);

        return jsonObject;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isWheelie() {
        return wheelie;
    }

    public void setWheelie(boolean wheelie) {
        this.wheelie = wheelie;
    }

    public Car(String name, boolean wheelie) {

        this.name = name;
        this.wheelie = wheelie;
    }
}
