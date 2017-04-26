package com.tathao.tathao.duoihinhbatchu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener {


    private Button btnPlay, btnExit, btnVolume, btnInfor;
    private TextView tvTilte, tvLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        init();
       // backGround();

    }


    private void init(){
        btnPlay = (Button)findViewById(R.id.btnPlay);
        btnExit = (Button)findViewById(R.id.btnExit);
        tvLevel = (TextView) findViewById(R.id.tvLevel);
        btnVolume = (Button)findViewById(R.id.btnVolume);
        btnInfor = (Button)findViewById(R.id.btnInfor);
        tvTilte = (TextView)findViewById(R.id.tvTitle);

        btnPlay.setOnClickListener(this);
        btnExit.setOnClickListener(this);
        btnVolume.setOnClickListener(this);
        btnInfor.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.btnPlay:{
                Intent i = new Intent(MainActivity.this, Game_PlayActivity.class);
                startActivity(i);
                break;
            }
            case R.id.btnExit:{
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
                Intent starMain = new Intent(Intent.ACTION_MAIN);
                starMain.addCategory(Intent.CATEGORY_HOME);
                startActivity(starMain);
                finish();
                break;
            }
            case R.id.btnVolume:{
                if(btnVolume.isClickable()){
                    btnVolume.setBackgroundResource(R.drawable.muted);
                }
                else{
                    btnVolume.setBackgroundResource(R.drawable.volume);
                }
                break;
            }
            case R.id.btnInfor:{
                Intent i = new Intent(MainActivity.this, Information_activity.class);
                startActivity(i);
                break;
            }
        }
    }
}
