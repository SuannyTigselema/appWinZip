package com.example.myapplication.Models;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.ArrayList;

public class adaptadorSD extends RecyclerView.Adapter<adaptadorSD.MyViewHolder> {
    private static final int TYPE_HEADER=0;
    private static final int TYPE_LIST=0;


    private Context mContext;

    private ArrayList<archivo> mLista;
    public TextView lblNombre;
    public TextView lblDescripcion;

    public adaptadorSD(Context context, ArrayList<archivo> lista) {
        mContext = context;
        mLista=lista;
    }

    //Métodos propios del RecyclerdView

    //infla los items al recyclerd
    @NonNull
    @Override
    public adaptadorSD.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
           view= LayoutInflater.from(mContext)
                   .inflate(R.layout.ly_itemarchivo,null,false);
           return new MyViewHolder(view);

    }

    //enlaza cada vista del viewholder con los datos de la Lista de productos
    @Override
    public void onBindViewHolder(@NonNull adaptadorSD.MyViewHolder holder, int position) {

        lblNombre.setText(mLista.get(position).getNombre());
        lblDescripcion.setText(mLista.get(position).getDescripcion());

    }

    //Devuelve la cantidad del elementos del recyclerd
    @Override
    public int getItemCount() {
        return mLista.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        int view_type;
        //Obtener los elementos q irán en cada item
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

                lblNombre=(TextView)itemView.findViewById(R.id.lblNombre);
                lblDescripcion=(TextView)itemView.findViewById(R.id.lblDescripcion);

        }
    }

}
