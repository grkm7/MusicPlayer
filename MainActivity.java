package com.info.lab5;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //Stop() komutunu yaptıktan sonra müziği tekrar başlatmak için
    //bir çok şey denedim fakat işe yaramadı .
    //Bu haftaki labın asıl konusu o olmadığı için denemeyi bıraktım hocam, iyi günler.
    private ImageView ivplay;
    private ImageView ivstop;
    private ImageView ivskip;
    private TextView tvSong;

    private int playpause_sayac=0;
    private int sarki_index=0;



    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ListView SongList= findViewById(R.id.listView);
        ivplay=findViewById(R.id.ivplay);
        ivstop=findViewById(R.id.ivstop);
        ivskip=findViewById(R.id.ivskip);
        tvSong=findViewById(R.id.tvSong);

        final MediaPlayer dancemonkey= MediaPlayer.create(this,R.raw.dancemonkey);
        final MediaPlayer luciddreams= MediaPlayer.create(this,R.raw.luciddreams);
        final MediaPlayer highestintheroom= MediaPlayer.create(this,R.raw.highestintheroom);

        final String Songs[]= getResources().getStringArray(R.array.Songs);

        ArrayAdapter Adapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1, android.R.id.text1, Songs);

        SongList.setAdapter(Adapter);

        SongList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(playpause_sayac%2==1){
                    tvSong.setText("Başka seçim yapmadan önce çalan şarkıyı durdur.");
                }else{
                    tvSong.setText(SongList.getItemAtPosition(position).toString()+" seçildi");
                    sarki_index=position;

                }



            }
        });

        ivplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playpause_sayac++;

                if(playpause_sayac%2==0){
                    ivplay.setImageResource(R.drawable.play);
                    tvSong.setText("Şuan "+SongList.getItemAtPosition(sarki_index).toString()+" durduruldu.");
                    if(sarki_index==0){
                        dancemonkey.pause();
                    }else if(sarki_index==1){
                        highestintheroom.pause();
                    }else {
                        luciddreams.pause();
                    }

                }else{


                    ivplay.setImageResource(R.drawable.pause);
                    tvSong.setText("Şuan "+SongList.getItemAtPosition(sarki_index).toString()+" çalıyor.");
                    if(sarki_index==0){
                        dancemonkey.start();
                    }else if(sarki_index==1){
                        highestintheroom.start();
                    }else {
                        luciddreams.start();
                    }

                }
            }
        });

        ivstop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tvSong.setText("Şuan "+SongList.getItemAtPosition(sarki_index).toString()+" durduruldu.");

                if(playpause_sayac%2==1){
                    if(sarki_index==0){
                        dancemonkey.pause();
                    }else if(sarki_index==1){
                        highestintheroom.pause();
                    }else {
                        luciddreams.pause();
                    }
                }else{
                    tvSong.setText("Çalan şarkı yok");

                }

                sarki_index=0;
                playpause_sayac=0;
                ivplay.setImageResource(R.drawable.play);

            }
        });

        ivskip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sarki_index==0){
                    dancemonkey.pause();

                }else if(sarki_index==1){
                    highestintheroom.pause();
                }else {
                    luciddreams.pause();
                }

                sarki_index++;
                if(sarki_index>=3){
                    sarki_index=0;
                }
                tvSong.setText("Şuan "+SongList.getItemAtPosition(sarki_index).toString()+" çalıyor");
                if(sarki_index==0){
                    dancemonkey.start();
                }else if(sarki_index==1){
                    highestintheroom.start();
                }else {
                    luciddreams.start();
                }

            }
        });

    }
}