package com.example.fabrikal.model;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.example.fabrikal.R;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class CamaraActivity extends Activity {
    static final int REQUEST_IMAGE_CAPTURE = 1;

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
            public void onClick(View view) {
                //abrirCamaraWithPermissionCheck();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @NeedsPermission(Manifest.permission.CAMERA)
    public void abrirCamara() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @OnShowRationale(Manifest.permission.CAMERA)
    public void showRationaleForCamera(PermissionRequest request) {
        showRationaleDialog("Queremos tomar fotos",request);
    }

    @OnPermissionDenied(Manifest.permission.CAMERA)
    public void onCameraDenied() {
        Toast.makeText(this,"permiso denegado", Toast.LENGTH_SHORT).show();
    }

    @OnNeverAskAgain(Manifest.permission.CAMERA)
    public void  onCameraNeverAskAgain() {
        Toast.makeText(this, "nunca preguntar", Toast.LENGTH_SHORT).show();
    }

    private void showRationaleDialog(String texto, PermissionRequest request) {
       AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setPositiveButton("Permitir" , (dialogInterface, i) -> {
            request.proceed();
        });

        alerta.setNegativeButton("Denegar", (dialogInterface, i) -> {
            request.cancel();
        });
        alerta.setMessage(texto);
        alerta.setCancelable(false);
        alerta.show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}

