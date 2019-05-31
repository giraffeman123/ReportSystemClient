package com.furukawa.reportsystem.reportsystemapp.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.furukawa.reportsystem.reportsystemapp.R;
import com.furukawa.reportsystem.reportsystemapp.api.model.Lider;

import java.util.List;

public class AdapterLider extends RecyclerView.Adapter<AdapterLider.MyViewHolder> implements View.OnClickListener {

    private List<Lider> mDataset;
    private View.OnClickListener listener;

    @NonNull
    @Override
    public AdapterLider.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.renglon_informacion_lider, parent, false);
        AdapterLider.MyViewHolder tvh = new AdapterLider.MyViewHolder(itemView);
        itemView.setOnClickListener(this);
        return tvh;

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.textViewNombre.setText(mDataset.get(position).getEmpleado().getNombre());
        holder.textViewArea.setText(mDataset.get(position).getArea());
        holder.textViewTurno.setText(mDataset.get(position).getEmpleado().getTurno());
        String linea = Integer.toString(mDataset.get(position).getLinea());
        holder.textViewLinea.setText(linea);
        holder.textViewCodigoEmpleado.setText(mDataset.get(position).getCodigoEmpleado());
        //holder.imageView.setImageResource(mDataset.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }


    public AdapterLider(List<Lider> mDataset) {
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

        public TextView textViewNombre;
        public TextView textViewCodigoEmpleado;
        public TextView textViewTurno;
        public TextView textViewArea;
        public TextView textViewLinea;


        public MyViewHolder(TextView v, TextView w, TextView x, TextView y, TextView t) {
            super(v);
            textViewNombre = v;
            textViewCodigoEmpleado = w;
            textViewArea = x;
            textViewLinea = y;
            textViewTurno = t;
        }
        public MyViewHolder(View v) {
            super(v);
            textViewNombre = v.findViewById(R.id.textViewNombre);
            textViewCodigoEmpleado = v.findViewById(R.id.textViewCodigoEmpleado);
            textViewArea = v.findViewById(R.id.textViewArea);
            textViewLinea = v.findViewById(R.id.textViewLinea);
            textViewTurno = v.findViewById(R.id.textViewTurno);

        }
    }
}
