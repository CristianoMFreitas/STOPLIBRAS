package com.example.jogocmlibras;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.MediaController;
import android.widget.Switch;
import android.widget.VideoView;

public class PlayerActivity extends AppCompatActivity {
    private VideoView videoView;
    private String videoName, videoCaminho;
    private Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        //Esconder ActionBar (barra no topo)
        getSupportActionBar().hide();

        videoView = findViewById(R.id.videoView);
        extras = getIntent().getExtras();
        videoName = extras.getString("videoNome");
        Log.i("testeInicio" , videoName);
        //executar o vídeo.

        if (videoName.equals("fimdejogo")){
            videoCaminho = "android.resource://" + getPackageName() + "/" + R.raw.fimdejogo;
            Log.i("teste" , videoCaminho);
        }else if(videoName.equals("novacm") ){
            videoCaminho = "android.resource://" + getPackageName() + "/" + R.raw.novacm;
            Log.i("teste" , videoCaminho);
        }else if(videoName.equals("pontos")){
            videoCaminho = "android.resource://" + getPackageName() + "/" + R.raw.pontos;
            Log.i("teste" , videoCaminho);
        }else if(videoName.equals("sinal")){
            videoCaminho = "android.resource://" + getPackageName() + "/" + R.raw.sinal;
            Log.i("teste" , videoCaminho);
        }else if(videoName.equals("tempo") ){
            videoCaminho = "android.resource://" + getPackageName() + "/" + R.raw.tempo;
            Log.i("teste" , videoCaminho);
        }

        videoView.setMediaController( new MediaController(this));
        videoView.setVideoPath(videoCaminho);
        videoView.start();

        Log.i("testeDuracao" , String.valueOf(videoView.getDuration()) );
    }
}