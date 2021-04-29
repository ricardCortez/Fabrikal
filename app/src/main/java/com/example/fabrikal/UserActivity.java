package com.example.fabrikal;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

//import prueba.app.firebase.myfirebaseapp.model.Usario;

public class UserActivity extends AppCompatActivity {

    EditText dniU, nombreU,apellidoU,tipo_direccionU,direccionU,nro_loteU,
            provinciaU,distritoU,celularU,emailU,contraseñaU,nro_tarjetaU,vigencia_tarjetaU,camaraU;
        ListView listV_usuarios;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        dniU = findViewById(R.id.txt_dniUsuario);
        nombreU = findViewById(R.id.txt_nombreUsuario);
        apellidoU = findViewById(R.id.txt_apellidoUsuario);
        tipo_direccionU = findViewById (R.id.txt_tipo_direcionUsurio);
        direccionU = findViewById(R.id.txt_direccionUsuario);
        nro_loteU  = findViewById(R.id.txt_nro_loteUsuario);
        provinciaU = findViewById(R.id.txt_provinciaUsuario);
        distritoU  = findViewById(R.id.txt_distritoUsuario);
        celularU   = findViewById(R.id.txt_celularUsuario);
        emailU     = findViewById(R.id.txt_emailUsuario);
        contraseñaU = findViewById(R.id.txt_contraseñaUsuario);
        nro_tarjetaU = findViewById(R.id.txt_nro_tarjetaUsuario);
        vigencia_tarjetaU = findViewById(R.id.txt_vigencia_tarjetaUsuario);
        camaraU =  findViewById(R.id.camara);

        listV_usuarios = findViewById(R.id.lv_datosUsuarios);
        inicializarFirebase();
    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    //@Override
    //public boolean onCreateOptionsMenu(Menu menu) {
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        String dni = dniU.getText().toString();
        String nombre = nombreU.getText().toString();
        String  apellido = apellidoU.getText().toString();
        String  tipo_direccion = tipo_direccionU.getText().toString();
        String  direccion = direccionU.getText().toString();
        String  nro_lote  = nro_loteU.getText().toString();
        String  provincia = provinciaU.getText().toString();
        String  distrito  = distritoU .getText().toString();
        String  celular   = celularU.getText().toString();
        String  email     = emailU.getText().toString();
        String  contraseña = contraseñaU.getText().toString();
        String  nro_tarjeta = nro_tarjetaU.getText().toString();
        String  vigencia_tarjeta = vigencia_tarjetaU.getText().toString();
        String  camara = camaraU.getText().toString();

        switch (item.getItemId()){
            case R.id.icon_add:{
                Toast.makeText(this,"Agregar", Toast.LENGTH_LONG).show();
                if (dni.equals("")||nombre.equals("")||apellido.equals("")||tipo_direccion.equals("")||direccion.equals("")||nro_lote.equals("")||provincia.equals("")||distrito.equals("")||celular.equals("")||email.equals("")||contraseña.equals("")||nro_tarjeta.equals("")||vigencia_tarjeta.equals("")||camara.equals("")){
                    validacion();
                }
                else {
                    Usuario u = new Usuario();
                    u.setUid(UUID.randomUUID().toString());
                    u.setDni(dni);
                    u.setNombre(nombre);
                    u.setApellido(apellido);
                    u.setTipo_direccion(tipo_direccion);
                    u.setDireccion(direccion);
                    u.setNro_lote(nro_lote);
                    u.setProvincia(provincia);
                    u.setDistrito(distrito);
                    u.setCelular(celular);
                    u.setEmail(email);
                    u.setContraseña(contraseña);
                    u.setNro_tarjeta(nro_tarjeta);
                    u.setVigencia_tarjeta(vigencia_tarjeta);
                    u.setCamara(camara);
                    databaseReference.child("Usuario").child(u.getuid()).setValue(u);
                    Toast.makeText(this, "Agregado", Toast.LENGTH_LONG).show();

                }
                break;
            }
            case R.id.icon_save:{
                Toast.makeText(this,"Actualizado", Toast.LENGTH_LONG).show();
                break;
            }
            case R.id.icon_delete:{
                Toast.makeText(this,"Eliminado", Toast.LENGTH_LONG).show();
                break;
            }
            default:break;
        }
        return true;
    }


    private void validacion() {
        String dni  = dniU.getText().toString();
        String nombre = nombreU.getText().toString();
        String apellido = apellidoU.getText().toString();
        String tipo_direccion = tipo_direccionU.getText().toString();
        String direccion = direccionU.getText().toString();
        String nro_lote = nro_loteU.getText().toString();
        String provincia = provinciaU.getText().toString();
        String distrito = distritoU.getText().toString();
        String celular = celularU.getText().toString();
        String email      = emailU.getText().toString();
        String contraseña = contraseñaU.getText().toString();
        String nro_tarjeta = nro_tarjetaU.getText().toString();
        String vigencia_tarjeta = vigencia_tarjetaU.getText().toString();
        String camara = camaraU.getText().toString();

        if (dni.equals("")){
            dniU.setError("Required");
        }
        else if (nombre.equals("")){
            nombreU.setError("Required");
        }
        else if (apellido.equals("")){
            apellidoU.setError("Required");
        }
        else if (tipo_direccion.equals("")){
            tipo_direccionU.setError("Required");
        }
        else if (direccion.equals("")){
            direccionU.setError("Required");
        }
        else if (nro_lote.equals("")){
            nro_loteU.setError("Required");
        }

        else if (provincia.equals("")){
            provinciaU.setError("Required");
        }

        else if (distrito.equals("")){
            distritoU.setError("Required");
        }

        else if (celular.equals("")){
            celularU.setError("Required");
        }

        else if (email.equals("")){
            emailU.setError("Required");
        }

        else if (contraseña.equals("")){
            contraseñaU.setError("Required");
        }

        else if (nro_tarjeta.equals("")){
            nro_tarjetaU.setError("Required");
        }

        else if (vigencia_tarjeta.equals("")){
            vigencia_tarjetaU.setError("Required");
        }

        else if (camara.equals("")){
            camaraU.setError("Required");
        }

    }
}




