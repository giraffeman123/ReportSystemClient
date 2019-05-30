package com.furukawa.reportsystem.reportsystemapp.ui.adapter;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.furukawa.reportsystem.reportsystemapp.R;
import com.furukawa.reportsystem.reportsystemapp.api.model.CodigoDefecto;

import java.util.List;

public class AdapterCodigoDefecto extends RecyclerView.Adapter<AdapterCodigoDefecto.MyViewHolder> implements View.OnClickListener {

    private List<CodigoDefecto> mDataset;
    private View.OnClickListener listener;

    @NonNull
    @Override
    public AdapterCodigoDefecto.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.renglon_informacion_codigo_defecto, parent, false);
        AdapterCodigoDefecto.MyViewHolder tvh = new AdapterCodigoDefecto.MyViewHolder(itemView);
        itemView.setOnClickListener(this);
        return tvh;

    }

    @Override
    public void onBindViewHolder(@NonNull AdapterCodigoDefecto.MyViewHolder holder, int position) {
        //holder.txtViewCodigoDefecto.setText(mDataset.get(position).getDefecto().getCodigoDefecto());
        holder.txtViewCodigoDefecto.setText(mDataset.get(position).getCodigoDefecto());
        holder.txtViewArea.setText(mDataset.get(position).getArea());
        holder.txtViewMaquina.setText(mDataset.get(position).getMaquina());
        String g = mDataset.get(position).getGravedad().toString();
        System.out.println("Gravedad:  " + g + " hola");
        if(g == "Bajo"){
            holder.txtViewGravedad.setBackgroundColor(Color.BLUE);
        }if(g == "Medio"){
            holder.txtViewGravedad.setBackgroundColor(Color.YELLOW);
        }if(g == "Alto"){
            holder.txtViewGravedad.setBackgroundColor(Color.RED);
        }
        holder.txtViewGravedad.setText(mDataset.get(position).getGravedad());
        holder.txtViewDescripcion.setText(mDataset.get(position).getDescripcion());
        //holder.imageView.setImageResource(mDataset.get(position).getImage());

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public AdapterCodigoDefecto(List<CodigoDefecto> mDataset) {
        this.mDataset=mDataset;
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if(listener != null)
            listener.onClick(view);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView txtViewCodigoDefecto;
        public TextView txtViewArea;
        public TextView txtViewMaquina;
        public TextView txtViewGravedad;
        public TextView txtViewDescripcion;


        public MyViewHolder(TextView u, TextView v, TextView w, TextView x, TextView y) {
            super(v);
            txtViewCodigoDefecto = u;
            txtViewArea = v;
            txtViewMaquina = w;
            txtViewGravedad = x;
            txtViewDescripcion = y;

        }
        public MyViewHolder(View v) {
            super(v);
            txtViewCodigoDefecto = v.findViewById(R.id.txtViewCodigoDefecto);
            txtViewArea = v.findViewById(R.id.txtViewLinea);
            txtViewMaquina = v.findViewById(R.id.txtViewMaquina);
            txtViewGravedad = v.findViewById(R.id.txtViewGravedad);
            txtViewDescripcion = v.findViewById(R.id.txtViewDescripcion);

            System.out.println("Gravedad:  " + txtViewGravedad.getText().toString() + " hola 2222");
            /*if(txtViewGravedad.getText() == "Bajo"){
                txtViewGravedad.setBackgroundColor(Color.BLUE);
            }if(txtViewGravedad.getText() == "Medio"){
                txtViewGravedad.setBackgroundColor(Color.YELLOW);
            }if(txtViewGravedad.getText() == "Alto"){
                txtViewGravedad.setBackgroundColor(Color.RED);
            }*/
        }
    }


}
