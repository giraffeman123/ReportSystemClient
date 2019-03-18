package com.furukawa.reportsystem.reportsystemapp.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.furukawa.reportsystem.reportsystemapp.R;
import com.furukawa.reportsystem.reportsystemapp.api.model.Lider;

import java.util.List;


public class LiderAdapter extends ArrayAdapter<Lider>{

    private Context context;
    private List<Lider> listaLideres;

    public LiderAdapter(Context context, List<Lider> listaLideres){
        super(context, R.layout.list_item_pagination,listaLideres);

        this.context = context;
        this.listaLideres = listaLideres;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;

        if (row == null) {
            LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.list_item_pagination, parent, false);
        }

        TextView textView = (TextView) row.findViewById(R.id.list_item_pagination_text);

        Lider item = listaLideres.get(position);
        String message = "Codigo: " + item.getCodigoEmpleado() + " Area: " + item.getArea() +" Linea: " + item.getLinea();
        textView.setText(message);

        return row;
    }
}
