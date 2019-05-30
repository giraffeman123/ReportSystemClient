package com.furukawa.reportsystem.reportsystemapp.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.furukawa.reportsystem.reportsystemapp.R;
import com.furukawa.reportsystem.reportsystemapp.api.model.Defecto;

import java.util.List;

public class AdapterDefectoEnLinea extends RecyclerView.Adapter<AdapterDefectoEnLinea.MyViewHolder> implements View.OnClickListener{

    /*
    Aun faltan cosas en el AdapterDefecto en Línea
     */

    private List<Defecto> mDataset;
    private View.OnClickListener listener;

    @NonNull
    @Override
    public AdapterDefectoEnLinea.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.renglon_informacion_defecto_en_linea, parent, false);
        AdapterDefectoEnLinea.MyViewHolder tvh = new AdapterDefectoEnLinea.MyViewHolder(itemView);
        itemView.setOnClickListener(this);
        return tvh;

    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDefectoEnLinea.MyViewHolder holder, int position) {
        holder.textViewCodigoDefecto.setText(mDataset.get(position).getCodigoDefecto());
        String linea = Integer.toString(mDataset.get(position).getLinea());
        holder.textViewLinea.setText(linea);
        String cantidad = Integer.toString(mDataset.get(position).getCantidad());
        holder.textViewCantidad.setText(cantidad);
        holder.textViewEstacion.setText(mDataset.get(position).getEstacion());
        //Falta la fecha
        //holder.textViewFecha.setText(mDataset.get(position).getFecha());
        holder.textViewComentario.setText(mDataset.get(position).getComentario());
        holder.textViewNombreLider.setText(mDataset.get(position).getLider().getEmpleado().getNombre());
        holder.textViewNombreAsociado.setText(mDataset.get(position).getAsociado().getNombre());

        //holder.imageView.setImageResource(mDataset.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }


    public AdapterDefectoEnLinea(List<Defecto> mDataset) {
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

        public TextView textViewCodigoDefecto;
        public TextView textViewLinea;
        public TextView textViewCantidad;
        public TextView textViewEstacion;
        public TextView textViewFecha;
        public TextView textViewComentario;
        public TextView textViewNombreLider;
        public TextView textViewNombreAsociado;


        public MyViewHolder(TextView v, TextView w, TextView x, TextView y) {
            super(v);
           /* textViewNombre = v;
            textViewCodigoEmpleado = w;
            textViewArea = x;
            textViewLinea = y;
            */
        }
        public MyViewHolder(View v) {
            super(v);
            /*
            textViewNombre = v.findViewById(R.id.textViewNombre);
            textViewCodigoEmpleado = v.findViewById(R.id.textViewCodigoEmpleado);
            textViewArea = v.findViewById(R.id.textViewArea);
            textViewLinea = v.findViewById(R.id.textViewLinea);
            */
        }
    }

}
