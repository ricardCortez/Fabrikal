package com.example.fabrikal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.JsonArrayRequest;
//import com.android.volley.toolbox.StringRequest;
//import com.android.volley.toolbox.Volley;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ProductosBuscarActivity extends AppCompatActivity {
    EditText e1,e2,e3,e4,e5,e6,e7,e8,e9,e10,e11,e12,e13,e14,e15,e16,e17,e18,e19,e20,e21;
    Button b, mostrar, buscar;

    RequestQueue requestQueue;
    private int ID_PRODUCTO;
    private int TIPO;
    private int MODELO;
    private int MARCA;
    private int COLOR;
    private char PRECIO_PRODUCTO;
    private char btnBuscar;
    private char acceso;



    public ProductosBuscarActivity() {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home);
        relacionamosVistas();

        mostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                e1.setText("");
                e2.setText("");
                e3.setText("");
                e4.setText("");
                e5.setText("");
                e6.setText("");
                e7.setText("");
                e8.setText("");
                e9.setText("");
                e10.setText("");
                e11.setText("");
                e12.setText("");
                e13.setText("");
                e14.setText("");
                e15.setText("");
                e16.setText("");
                e17.setText("");
                e18.setText("");
                e19.setText("");
                e20.setText("");
                e21.setText("");
                b=(Button) findViewById(acceso);
                buscar = (Button)findViewById(btnBuscar);

            }
        });
        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscar_producto("http://http://192.168.1.33/FABRIKALSHOES/buscar_producto.php?modelo"+e3.getText()+"");
            }
        });
    }
    public void relacionamosVistas(){
        e1=(EditText)findViewById(ID_PRODUCTO);
        e2=(EditText)findViewById(TIPO);
        e3=(EditText)findViewById(MODELO);
        e4=(EditText)findViewById(MARCA);
        e5=(EditText)findViewById(COLOR);
        e6=(EditText)findViewById(PRECIO_PRODUCTO);

    }

    public void validar(View v){

        final String DNI=e7.getText().toString();
        final String NOMBRE=e8.getText().toString();
        final String APELLIDO=e9.getText().toString();
        final String TIPO_DIRECCION=e10.getText().toString();
        final String DIRECCION=e11.getText().toString();
        final String NRO_LOTE=e12.getText().toString();
        final String PROVINCIA=e13.getText().toString();
        final String DISTRITO=e14.getText().toString();
        final String CELULAR=e15.getText().toString();
        final String EMAIL=e16.getText().toString();
        final String CONTRASEÑA=e17.getText().toString();
        final String NRO_TARJETA=e18.getText().toString();
        final String VIGENCIA_TARJETA=e19.getText().toString();
        final String FOTO_USUARIO=e20.getText().toString();


        String url="http://192.168.1.33/FABRIKALSHOES/ingreso.php?dni="+DNI+"nombre="+NOMBRE+"&apellido="+APELLIDO+"&tipo_direccion="+TIPO_DIRECCION+"&direccion="+DIRECCION+"&nro_lote="+NRO_LOTE
                +PROVINCIA+"&provincia="+DISTRITO+"&provincia="+CELULAR+"&celular="+EMAIL+"&email="+CONTRASEÑA+"&contraseña="+NRO_TARJETA+"&nro_tarjeta="+VIGENCIA_TARJETA+"&vigencia="+FOTO_USUARIO+"&foto_usuario=";
        RequestQueue servicio= Volley.newRequestQueue(this);
        StringRequest respuesta=new StringRequest(
                Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(),
                        response,Toast.LENGTH_LONG).show();


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),
                        "Error comunicación",Toast.LENGTH_SHORT).show();
            }
        });
        servicio.add(respuesta);
    }

    public void buscar_producto(String URL){
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        e1.setText(jsonObject.getString("ID_PRODUCTO"));
                        e2.setText(jsonObject.getString("TIPO"));
                        e3.setText(jsonObject.getString("MODELO"));
                        e4.setText(jsonObject.getString("MARCA"));
                        e5.setText(jsonObject.getString("COLOR"));
                        e6.setText(jsonObject.getString("PRECIO_PRODUCTO"));


                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error de Conexión", Toast.LENGTH_SHORT).show();
            }
        }
        );
        requestQueue=Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);

    }
    public void regresar(View view) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}
