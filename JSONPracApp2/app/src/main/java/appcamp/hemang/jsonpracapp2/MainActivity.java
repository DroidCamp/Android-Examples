package appcamp.hemang.jsonpracapp2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Music> musicList = new ArrayList<>();
        MusicAdapter musicAdapter = new MusicAdapter();


    }
}
