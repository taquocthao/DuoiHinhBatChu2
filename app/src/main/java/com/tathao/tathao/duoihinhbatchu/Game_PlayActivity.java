package com.tathao.tathao.duoihinhbatchu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by USER on 3/24/2017.
 */

public class Game_PlayActivity extends Activity implements View.OnClickListener {

    private ImageView imgPicture;

    private int rd;
    private Random random = new Random();
    private Button[] btnSuggest;
    private LinearLayout layout_DapAn;
    private LinearLayout layout_ButtonPick1;
    private LinearLayout layout_ButtonPick2;
    private TextView tvLevel;
    private int index = 0;
    private int count = 0;
    private String ketqua = "";
    //private int level = 0;

    private Button btnback;

    public static final int[] Question = {
            R.drawable.hoidong,
            R.drawable.aomua,
            R.drawable.baocao,
            R.drawable.oto,
            R.drawable.danong,
            R.drawable.xakep,
            R.drawable.xaphong,
            R.drawable.tohoai,
            R.drawable.canthiep,
            R.drawable.cattuong,
            R.drawable.danhlua,
            R.drawable.tichphan,
            R.drawable.quyhang,
            R.drawable.giangmai,
            R.drawable.giandiep,
            R.drawable.songsong,
            R.drawable.thothe,
            R.drawable.thattinh,
            R.drawable.tranhthu,
            R.drawable.totien,
            R.drawable.masat,
            R.drawable.hongtam
    };

    private final String[] DapAn ={
            "HOIDONG",
            "AOMUA",
            "BAOCAO",
            "OTO",
            "DANONG",
            "XAKEP",
            "XAPHONG",
            "TOHOAI",
            "CANTHIEP",
            "CATTUONG",
            "DANHLUA",
            "TICHPHAN",
            "QUYHANG",
            "GIANGMAI",
            "GIANDIEP",
            "SONGSONG",
            "THOTHE",
            "THATTINH",
            "TRANHTHU",
            "TOTIEN",
            "MASAT",
            "HONGTAM"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_game_play);
        init();

        rd = ranDom();
        createImage();
        createButtonAnswer();
        createButtonPick();

    }

    public void init(){
        btnback = (Button)findViewById(R.id.btnReturnMain);
        imgPicture = (ImageView) findViewById(R.id.imgPicture);
        tvLevel = (TextView)findViewById(R.id.tvLevel);

        btnback.setOnClickListener(this);
    }

    public void createImage() {
        imgPicture.setBackgroundResource(Question[rd]);
    }

    public int ranDom() {
        ArrayList<Integer> arrRD = new ArrayList<Integer>();
        int rdNumber = 0;
        while (check(arrRD, rdNumber)) {
            rdNumber = random.nextInt(Question.length);
            arrRD.add(rdNumber);
        }
        return rdNumber;
    }

    public boolean check(ArrayList<Integer> arr, int n){
        for(int i = 0; i < arr.size(); i++){
            if(n == arr.get(i))
                return false;
        }
        return true;
    }


    // dãy ô đáp án
    public void createButtonAnswer(){
        layout_DapAn = (LinearLayout)findViewById(R.id.layout_DapAn);
        btnSuggest = new Button[DapAn[rd].length()];
        for(int i = 0; i < DapAn[rd].length(); i++){
            Button btn = new Button(this);
            btn.setLayoutParams(new LinearLayout.LayoutParams(90, 90));
            btn.setId(i);
            btn.setTag("100");
            //
            btn.setOnClickListener(this);
            //
            btn.setBackgroundResource(R.drawable.button_xam);
            layout_DapAn.addView(btn);

            btnSuggest[i] = (Button)findViewById(btn.getId());
        }
    }

    // dãy chữ gợi ý
    public ArrayList createCharSuggest(){
        ArrayList<String> arr = new ArrayList<String>();
        int tm = random.nextInt(25) + 65;
        for(int i = 0; i < DapAn[rd].length(); i++){
            arr.add(DapAn[rd].charAt(i)+ "");
        }
        for(int i = 0; i < 16 - DapAn[rd].length(); i++){
            arr.add((char)tm+"");
        }
        return arr;
    }

    public void createButtonPick(){
        layout_ButtonPick1 = (LinearLayout)findViewById(R.id.layout_Button1);
        layout_ButtonPick2 = (LinearLayout)findViewById(R.id.layout_Button2);
        ArrayList<Integer> arr = new ArrayList<Integer>();
        for(int i = 0; i < 8; i++){
            Button btn = new Button(this);
            btn.setLayoutParams(new LinearLayout.LayoutParams(90,90));
            btn.setBackgroundResource(R.drawable.tile_hover);

            btn.setTag("102");
            btn.setOnClickListener(this);
            while (btn.getText() == ""){
                int tmp = random.nextInt(16);
                if(check(arr,tmp)){
                    btn.setText((CharSequence) createCharSuggest().get(tmp));
                    createCharSuggest().remove(tmp);
                    arr.add(tmp);
                }
            }
            layout_ButtonPick1.addView(btn);
        }

        for(int i = 0; i < 8; i++) {
            Button btn = new Button(this);
            btn.setLayoutParams(new LinearLayout.LayoutParams(90, 90));
            btn.setBackgroundResource(R.drawable.tile_hover);
            btn.setOnClickListener(this);
            btn.setTag("103");
            while (btn.getText() == "") {
                int tmp = random.nextInt(16);
                if (check(arr, tmp)) {
                    btn.setText((CharSequence) createCharSuggest().get(tmp));
                    createCharSuggest().remove(tmp);
                    arr.add(tmp);
                }
            }
            layout_ButtonPick2.addView(btn);
        }
    }

    @Override
    public void onClick(View view) {

        Button button = (Button)view;


        if(view.getId() == R.id.btnReturnMain) {
            Intent intentMain = new Intent(Game_PlayActivity.this, MainActivity.class);
            startActivity(intentMain);
        }
        if(view.getTag() == "100"){
            button.setText(btnSuggest[index].getText());
            btnSuggest[index].setText("");
            --index;
        }
        else
        {
            btnSuggest[index].setText(button.getText());
            ketqua += button.getText();
            index++;
            count++;
            view.setEnabled(false);
            view.setBackgroundColor(601800);
            ((Button) view).setText("");

            if(count == DapAn[rd].length()){
                if(ketqua.equals(DapAn[rd])){
                    for(int i = 0; i < DapAn[rd].length(); i++){
                        btnSuggest[i].setBackgroundResource(R.drawable.tile_true);
                    }
                   // level++;
                    Intent intent = new Intent(Game_PlayActivity.this, Congratulations_activity.class);
                    startActivity(intent);
                }
                else{
                    for(int i = 0; i < DapAn[rd].length(); i++){
                        btnSuggest[i].setBackgroundResource(R.drawable.tile_false);
                    }
                    Toast.makeText(Game_PlayActivity.this, "Sai rồi diễm ơi!!!", Toast.LENGTH_SHORT).show();
                    index = 0;
                    ketqua = "";
                    count = 0;
                    layout_DapAn.removeAllViews();
                    layout_ButtonPick1.removeAllViews();
                    layout_ButtonPick2.removeAllViews();

                    createButtonAnswer();
                    createButtonPick();
                }
            }

        }

    }
}
