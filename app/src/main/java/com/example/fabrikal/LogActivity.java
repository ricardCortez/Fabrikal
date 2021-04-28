package com.example.fabrikal;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

public class LogActivity extends AppCompatActivity {
    Button btn_signUp, btn_signIn;
    EditText email, password;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private Object AlertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        btn_signUp = findViewById(R.id.btn_signUp);
        btn_signIn  = findViewById(R.id.btn_signIn);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        setup();
    }

    private void setup() {
        setTitle("Inicio");
        btn_signUp .setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
               if((email.getText().toString()).isEmpty() && (password.getText().toString()).isEmpty() ) {
                   showAlertNull();
               }
            }

            private void showAlertNull() {
            }
        });
    }

}
