package appcamp.hemang.jprac;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hemang on 05/07/16.
 */
public class Serializer {

    public String filename;
    public Context context;

    public Serializer(String filename, Context context) {
        this.filename = filename;
        this.context = context;
    }

    public void save(ArrayList<Car> cars) throws JSONException, IOException {
        JSONArray jsonArray = new JSONArray();

        for(Car c: cars){
            jsonArray.put(c.makeItJSON());
        }


        //Why null!?
        //Just initialize Writer writer; and go ahead. It will automatically ask to add null.
        // :D

        Writer writer = null;
        OutputStream outputStream;

        try {
            outputStream = context.openFileOutput(filename, Context.MODE_PRIVATE);
            writer = new OutputStreamWriter(outputStream);
            writer.write(jsonArray.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer!=null) writer.close();
        }
    }

    public ArrayList<Car> load() throws IOException, JSONException {

        ArrayList<Car> cars = new ArrayList<>();


        //This null will automatically be asked to be added by IDE
        BufferedReader reader = null;
        InputStream inputStream;

        try{
            inputStream = context.openFileInput(filename);
            reader = new BufferedReader(new InputStreamReader(inputStream));



            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine())!=null){
                stringBuilder.append(line);
            }

            JSONArray jsonArray = (JSONArray) new JSONTokener(stringBuilder.toString()).nextValue();

            for(int i = 0; i < jsonArray.length(); i++){
                cars.add(new Car(jsonArray.getJSONObject(i)));
            }


        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (reader!=null) reader.close();
        }

        return cars;
    }


}
