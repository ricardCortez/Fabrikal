package com.example.fabrikal.adapters;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fabrikal.R;
import com.example.fabrikal.producto;

import java.util.List;


public class AdaptersProducto extends RecyclerView.Adapter<AdaptersProducto.viewholderproductos>{

    List<producto> productoList;

    public AdaptersProducto(List<producto> productoList) {
        this.productoList = productoList;
    }

    @NonNull
    @Override
    public viewholderproductos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_product,parent,false);
        viewholderproductos holder = new viewholderproductos(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull viewholderproductos holder, int position) {
     producto p =  productoList.get(position);

     holder.tv_tipo.setText(p.getTipo());
     holder.tv_color.setText(p.getColor());
     holder.tv_marca.setText(p.getMarca());
     holder.tv_modelo.setText(p.getModelo());
     holder.tv_precio.setText(p.getPrecio());
    }

    @Override
    public int getItemCount() {
        return productoList.size();
    }

    public class viewholderproductos extends RecyclerView.ViewHolder {
        TextView tv_tipo,tv_color,tv_marca,tv_modelo,tv_precio;

        public viewholderproductos(@NonNull View itemView) {
            super(itemView);

            tv_tipo = itemView.findViewById(R.id.tv_tipo);
            tv_color = itemView.findViewById(R.id.tv_color);
            tv_marca = itemView.findViewById(R.id.tv_marca);
            tv_modelo = itemView.findViewById(R.id.tv_modelo);
            tv_precio = itemView.findViewById(R.id.tv_precio);
        }
    }

}
