package com.miso.menu;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.miso.thegame.MultiplayerLobby;
import com.miso.thegame.gameMechanics.map.levels.NewLevelActivity;


public class MenuActivity extends Activity {

    private Intent gameIntent = null;
    private MediaPlayer mMediaPlayer;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mMediaPlayer = new MediaPlayer();
        mMediaPlayer = MediaPlayer.create(this, R.raw.intro);
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mMediaPlayer.setLooping(false);
        //mMediaPlayer.start();
    }

    public void onResume(){
        super.onResume();
        setContentView(R.layout.activity_menu);
    }

    public void newGameClickGround(View view) {
        setContentView(R.layout.loading_game);
        this.gameIntent = new Intent(this, NewLevelActivity.class);
        this.gameIntent.putExtra("Level", "Ground");
        startActivity(this.gameIntent);
    }

    public void newMultiplayerGame(View view){
        this.gameIntent = new Intent(this, MultiplayerLobby.class);
        this.gameIntent.putExtra("Level", "Default");
        startActivity(this.gameIntent);
    }

    public void onDestroy(){
        mMediaPlayer.stop();
        super.onDestroy();
    }

    public void playerOptionClick(View view){
        Intent intent = new Intent(this, PlayerOptions.class);
        startActivity(intent);
    }

    public void quitClick(View viev){
        this.finish();
        System.exit(0);
    }
}
