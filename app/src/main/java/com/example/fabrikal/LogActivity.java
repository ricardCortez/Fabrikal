package com.example.fabrikal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class LogActivity extends AppCompatActivity {
    Button btn_signUp, btn_signIn;
    EditText email, password;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private Object AlertDialog;
    private Intent homeIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        btn_signUp = findViewById(R.id.btn_signUp);
        btn_signIn = findViewById(R.id.btn_signIn);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        setup(homeIntent);
    }

    private void setup(Intent homeIntent) {
        setTitle("Inicio");
        btn_signUp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if ((email.getText().toString()).isEmpty() && (password.getText().toString()).isEmpty()) {
                    showAlertnull();
                } else {
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email.getText().toString(),
                            password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                String uid = task.getResult().getUser().getUid();
                                String email = task.getResult().getUser().getEmail();
                                showAgenda(email);
                                Map<String, Object> user = new HashMap<>();
                                user.put("uid", uid);
                                user.put("email", email);
                                db.collection("users").add(user);
                            } else {
                                showAlertRegistrado();
                            }
                        }
                    });
                }
            }

        });

        btn_signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (email.getText().toString().isEmpty() && password.getText().toString().isEmpty()) {
                    showAlertnull();

                } else {
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(email.getText().toString(),
                            password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                String email = task.getResult().getUser().getEmail();
                                showAgenda(email);

                            } else {
                                showAlertUsuarioNR();
                            }
                        }
                    });
                }
            }
        });
    }
private void  showAlertnull(){
        AlertDialog = new AlertDialog.Builder(this);
        ((AlertDialog.Builder)AlertDialog).setTitle("Error");
        ((AlertDialog.Builder)AlertDialog).setMessage("Por favor Rellene los campos");
        ((AlertDialog.Builder)AlertDialog).setPositiveButton("Aceptar", null);
        androidx.appcompat.app.AlertDialog dialog = ((AlertDialog.Builder)AlertDialog).create();
        dialog.show();
}
private void  showAlertRegistrado(){
    AlertDialog = new AlertDialog.Builder(this);
    ((AlertDialog.Builder)AlertDialog).setTitle("Error");
    ((AlertDialog.Builder)AlertDialog).setMessage("El usuario ya se encuentra resgistrado");
    ((AlertDialog.Builder)AlertDialog).setPositiveButton("Aceptar", null);
    androidx.appcompat.app.AlertDialog dialog = ((AlertDialog.Builder)AlertDialog).create();
    dialog.show();
}

private void  showAlertUsuarioNR(){
    AlertDialog = new AlertDialog.Builder(this);
    ((AlertDialog.Builder)AlertDialog).setTitle("Error");
    ((AlertDialog.Builder)AlertDialog).setMessage("El usuario no se encuentra resgistrado");
    ((AlertDialog.Builder)AlertDialog).setPositiveButton("Aceptar", null);
    androidx.appcompat.app.AlertDialog dialog = ((AlertDialog.Builder)AlertDialog).create();
    dialog.show();
}
private void  showAgenda(String email){
Intent homeIntent = new Intent(this,UserActivity.class).putExtra("email", email);
      setup(homeIntent);
   }

}