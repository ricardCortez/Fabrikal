package com.example.fabrikal.model;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.fabrikal.R;

public class CamaraActivity extends Activity {
    Button buttonCamara;
    ImageView vistaCamara;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camara);
        buttonCamara = findViewById(R.id.buttonCamara);
        vistaCamara = findViewById(R.id.vistaCamara);

        buttonCamara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirCamara();
            }
        });
    }
    private void abrirCamara(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //if(intent.resolveActivity(getPackageManager()) !=null){
            startActivityForResult(intent,1);
        //}
    }
}

