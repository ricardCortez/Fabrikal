package com.example.fabrikal;

import android.os.Bundle;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fabrikal.adapters.AdaptersProducto;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ActivityProducto extends AppCompatActivity {
    DatabaseReference ref;
    ArrayList<producto> list;
    RecyclerView rv;
    SearchView searchView;
    AdaptersProducto adapter;
    LinearLayoutManager lm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home);

        ref = FirebaseDatabase.getInstance().getReference().child("producto");
        rv = findViewById(R.id.rv);
        searchView = findViewById(R.id.searchView);
        lm = new LinearLayoutManager(this);
        rv.setLayoutManager(lm);
        list = new ArrayList<>();
        adapter = new AdaptersProducto(list);
        rv.setAdapter(adapter);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (DataSnapshot.exists()) {
                    for (DataSnapshot snapshot1 : DataSnapshot.getChildren()) {
                        producto p = snapshot.getValue(producto.class);
                        list.add(p);
                    }
                    adapter.notify();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                buscar(s);
                return true;
            }
        });
    }

    private void buscar(String s) {
        ArrayList<producto>milista = new ArrayList<>();
        for (producto obj: list){
            if(obj.getModelo().toLowerCase().contains(s.toLowerCase())){
                milista.add(obj);
            }
        }
        AdaptersProducto adapter = new AdaptersProducto(milista);
        rv.setAdapter(adapter);
    }
}
