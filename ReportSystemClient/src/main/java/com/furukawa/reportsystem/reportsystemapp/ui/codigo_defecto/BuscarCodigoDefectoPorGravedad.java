package com.furukawa.reportsystem.reportsystemapp.ui.codigo_defecto;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import com.furukawa.reportsystem.reportsystemapp.R;
import com.furukawa.reportsystem.reportsystemapp.api.model.CodigoDefecto;
import com.furukawa.reportsystem.reportsystemapp.api.service.ApiUtils;
import com.furukawa.reportsystem.reportsystemapp.api.service.ReportSystemInterface;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BuscarCodigoDefectoPorGravedad extends android.support.v4.app.Fragment{

    private List<CodigoDefecto> codigoDefecto;
    private ReportSystemInterface api;
    private OnSendingCodigoDefectoListener listener;

    public BuscarCodigoDefectoPorGravedad() {
        // Required empty public constructor
    }

    public interface OnSendingCodigoDefectoListener{
        void OnInputBuscarCodigoDefectoGravedadSent(List<CodigoDefecto> codigoDefecto);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        api = ApiUtils.getReportSystemService();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_buscar_codigo_defecto_por_gravedad, container, false);
        final Button btAceptar = v.findViewById(R.id.btAceptar);

        final Spinner Gravedad = (Spinner) v.findViewById(R.id.spGravedadBuscar);

        ArrayList<String> arraySpinnerGravedad = new ArrayList<>();
        arraySpinnerGravedad.add("Seleccione una");
        arraySpinnerGravedad.add("Bajo");
        arraySpinnerGravedad.add("Medio");
        arraySpinnerGravedad.add("Alto");

        ArrayAdapter<String> adapterGravedad = new ArrayAdapter<String>(this.getActivity().getApplicationContext(),
                android.R.layout.simple_spinner_dropdown_item,arraySpinnerGravedad);

        Gravedad.setAdapter(adapterGravedad);

        btAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String txtGravedad = Gravedad.getSelectedItem().toString();

                if(txtGravedad.equals("Seleccione una")){
                    Toast.makeText(getActivity(),"Seleccione un tipo de gravedad",Toast.LENGTH_LONG).show();
                }else{
                    buscarCodigoDefectoPorGravedad(Gravedad.getSelectedItem().toString());
                }
            }
        });
        return v;
    }

    public void buscarCodigoDefectoPorGravedad(final String gravedad){
        if(!gravedad.isEmpty()){
            Call<List<CodigoDefecto>> call = api.allCodigoDefectoByGravedad(gravedad);
            call.enqueue(new Callback<List<CodigoDefecto>>() {
                @Override
                public void onResponse(Call<List<CodigoDefecto>> call, Response<List<CodigoDefecto>> response) {
                    if(!response.isSuccessful()){
                        Toast.makeText(getActivity(),"Code: "+response.code(),Toast.LENGTH_LONG).show();
                    }
                    if(response.body() != null){
                        codigoDefecto = response.body();
                        listener.OnInputBuscarCodigoDefectoGravedadSent(codigoDefecto);
                        // Toast.makeText(getActivity(),"Código Defecto encontrado.",Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(getActivity(),"Código Defecto no encontrado.",Toast.LENGTH_LONG).show();
                    }
                }
                @Override
                public void onFailure(Call<List<CodigoDefecto>> call, Throwable t) {

                }
            });
        }else{
            Toast.makeText(getActivity(),"Seleccione un tipo de gravedad a buscar",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnSendingCodigoDefectoListener) {
            listener = (OnSendingCodigoDefectoListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnSendingCodigoDefectoListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}
