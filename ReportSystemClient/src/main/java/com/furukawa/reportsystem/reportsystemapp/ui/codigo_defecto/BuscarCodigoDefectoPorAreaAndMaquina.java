package com.furukawa.reportsystem.reportsystemapp.ui.codigo_defecto;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BuscarCodigoDefectoPorAreaAndMaquina.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class BuscarCodigoDefectoPorAreaAndMaquina extends Fragment {

    private List<CodigoDefecto> codigoDefecto;
    private ReportSystemInterface api;
    private OnSendingCodigoDefectoListener listener;

    public BuscarCodigoDefectoPorAreaAndMaquina() {
        // Required empty public constructor
    }

    public interface OnSendingCodigoDefectoListener{
        void OnInputBuscarCodigoDefectoSent(List<CodigoDefecto> codigoDefecto);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        api = ApiUtils.getReportSystemService();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_buscar_codigo_defecto_por_area_maquina, container, false);
        final EditText codigoEdTxt = v.findViewById(R.id.edtxrIngresarCodigo);
        final Button btAceptar = v.findViewById(R.id.btAceptar);

        final Spinner Area = (Spinner) v.findViewById(R.id.spGravedadBuscar);
        final Spinner Maquina = (Spinner) v.findViewById(R.id.spMaquinaBuscar);

        ArrayList<String> arraySpinnerArea = new ArrayList<>();
        arraySpinnerArea.add("Seleccione una");
        arraySpinnerArea.add("Fusebox");
        arraySpinnerArea.add("SRC");
        arraySpinnerArea.add("Ensamblaje");
        arraySpinnerArea.add("Produccion");

        ArrayList<String> arraySpinnerMaquina = new ArrayList<>();
        arraySpinnerMaquina.add("Seleccione una");
        arraySpinnerMaquina.add("Basbar Press");
        arraySpinnerMaquina.add("Bara Busbar Injection");
        arraySpinnerMaquina.add("Greaser Machine");
        arraySpinnerMaquina.add("Fuse Press");
        arraySpinnerMaquina.add("Multi Function Press");
        arraySpinnerMaquina.add("Final Tester");


        ArrayAdapter<String> adapterArea = new ArrayAdapter<String>(this.getActivity().getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,arraySpinnerArea);
        ArrayAdapter<String> adapterMaquina = new ArrayAdapter<String>(this.getActivity().getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,arraySpinnerMaquina);

        Area.setAdapter(adapterArea);
        Maquina.setAdapter(adapterMaquina);

        btAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String txtArea = Area.getSelectedItem().toString();
                String txtMaquina = Maquina.getSelectedItem().toString();

                if(txtArea.equals("Seleccione una")
                        || txtMaquina.equals("Seleccione una")){

                    Toast.makeText(getActivity(),"Hay un campo vacío. Antes de presionar agregar llene todos los campos."
                            ,Toast.LENGTH_LONG).show();
                }else{

                    buscarCodigoDefecto(Area.getSelectedItem().toString(),Maquina.getSelectedItem().toString());
                }
                }
        });
        return v;
    }

    public void buscarCodigoDefecto(final String area, final String maquina){
        if(!area.isEmpty()&& !maquina.isEmpty()){
            Call<List<CodigoDefecto>> call = api.allCodigoDefectoByAreaAndMaquina(area,maquina);
            call.enqueue(new Callback<List<CodigoDefecto>>() {
                @Override
                public void onResponse(Call<List<CodigoDefecto>> call, Response<List<CodigoDefecto>> response) {
                    if(!response.isSuccessful()){
                        Toast.makeText(getActivity(),"Code: "+response.code(),Toast.LENGTH_LONG).show();
                    }
                    if(response.body() != null){
                        codigoDefecto = response.body();
                        listener.OnInputBuscarCodigoDefectoSent(codigoDefecto);
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
            Toast.makeText(getActivity(),"Ingrese un código a buscar",Toast.LENGTH_LONG).show();
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
