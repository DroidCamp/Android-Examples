package appcamp.hemang.videointentapp;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.VideoProfile;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button bt , playButton;
    VideoView videoView;
    final int vidRequest = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt = (Button) findViewById(R.id.button);
        playButton = (Button) findViewById(R.id.button2);
        bt.setOnClickListener(this);
        playButton.setOnClickListener(this);

        videoView = (VideoView) findViewById(R.id.videoView);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button: videoCapture();
                break;

            case R.id.button2: pausePlayVideo();
                break;
        }

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == vidRequest && resultCode == RESULT_OK){

            //VIDEO PROCESSING


            //getData() is called when a Path is returned by intent

            Uri videoUri = data.getData();
            videoView.setVideoPath(getRealPathFromURI(videoUri));
        }
    }


    public void videoCapture(){
        Intent video = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        startActivityForResult(video, vidRequest);
    }

    public void pausePlayVideo(){
        if(videoView.isPlaying()){
            playButton.setText("PAUSE VIDEO");
            videoView.pause();
        }

        else{
            playButton.setText("PLAY VIDEO");
            videoView.start();
        }

    }

    public String getRealPathFromURI(Uri contentUri){
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        int column_in = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

        cursor.moveToFirst();
        return cursor.getString(column_in);
    }


}
