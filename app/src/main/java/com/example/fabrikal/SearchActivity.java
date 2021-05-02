package com.example.fabrikal;

import android.os.Bundle;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fabrikal.adapters.ShoesHomeAdapter;
import com.example.fabrikal.model.ShoeHomeItem;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {
    DatabaseReference ref;
    ArrayList<ShoeHomeItem> list;
    RecyclerView rv;
    SearchView searchView;
    ShoesHomeAdapter adapter;
    LinearLayoutManager lm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ref = FirebaseDatabase.getInstance().getReference("Producto");
        rv = findViewById(R.id.searchRecyclerView);
        searchView = findViewById(R.id.searchView);
        lm = new LinearLayoutManager(this);
        rv.setLayoutManager(lm);
        list = new ArrayList<>();
        adapter = new ShoesHomeAdapter(new ArrayList<>());
        rv.setAdapter(adapter);


        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        ShoeHomeItem p = snapshot.getValue(ShoeHomeItem.class);
                        list.add(p);
                    }
                }

               adapter.updateList(list);
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
        ArrayList<ShoeHomeItem> milista = new ArrayList<>();
        for (ShoeHomeItem obj: list){
            if(obj.getModelo().toLowerCase().contains(s.toLowerCase())){
                milista.add(obj);
            }
        }

        adapter.updateList(milista);

    }
}
