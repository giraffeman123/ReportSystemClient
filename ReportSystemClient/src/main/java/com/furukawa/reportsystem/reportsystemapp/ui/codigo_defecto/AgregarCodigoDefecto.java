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
import com.furukawa.reportsystem.reportsystemapp.api.service.ApiUtils;
import com.furukawa.reportsystem.reportsystemapp.api.service.ReportSystemInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AgregarCodigoDefecto.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AgregarCodigoDefecto#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AgregarCodigoDefecto extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ReportSystemInterface api;

    private OnFragmentInteractionListener mListener;

    public AgregarCodigoDefecto() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AgregarCodigoDefecto.
     */
    // TODO: Rename and change types and number of parameters
    public static AgregarCodigoDefecto newInstance(String param1, String param2) {

        AgregarCodigoDefecto fragment = new AgregarCodigoDefecto();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        api = ApiUtils.getReportSystemService();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_agregar_codigo_defecto, container, false);

        final Spinner Area = (Spinner) v.findViewById(R.id.spArea);
        final Spinner Linea = (Spinner) v.findViewById(R.id.spLinea);
        final Spinner Maquina = (Spinner) v.findViewById(R.id.spMaquina);
        final Spinner Estacion = (Spinner) v.findViewById(R.id.spEstacion);
        final Spinner Gravedad = (Spinner) v.findViewById(R.id.spGravedad);
        final EditText Descripcion = (EditText) v.findViewById(R.id.edtxtDescripcion);

        final Button btAceptar = (Button) v.findViewById(R.id.btAceptar);

        ArrayList<String> arraySpinnerArea = new ArrayList<>();
        arraySpinnerArea.add("Seleccione una");
        arraySpinnerArea.add("Fusebox");
        arraySpinnerArea.add("SRC");
        arraySpinnerArea.add("Ensamblaje");
        arraySpinnerArea.add("Produccion");

        ArrayList<String> arraySpinnerLinea = new ArrayList<>();
        arraySpinnerLinea.add("Seleccione una");
        arraySpinnerLinea.add("1");
        arraySpinnerLinea.add("2");
        arraySpinnerLinea.add("3");
        arraySpinnerLinea.add("4");
        arraySpinnerLinea.add("5");
        arraySpinnerLinea.add("6");
        arraySpinnerLinea.add("7");

        ArrayList<String> arraySpinnerMaquina = new ArrayList<>();
        arraySpinnerMaquina.add("Seleccione una");
        arraySpinnerMaquina.add("Basbar Press");
        arraySpinnerMaquina.add("Bara Busbar Injection");
        arraySpinnerMaquina.add("Greaser Machine");
        arraySpinnerMaquina.add("Fuse Press");
        arraySpinnerMaquina.add("Multi Function Press");
        arraySpinnerMaquina.add("Final Tester");

        ArrayList<String> arraySpinnerEstacion = new ArrayList<>();
        arraySpinnerEstacion.add("Seleccione una");
        arraySpinnerEstacion.add("1");
        arraySpinnerEstacion.add("2");
        arraySpinnerEstacion.add("3");
        arraySpinnerEstacion.add("4");
        arraySpinnerEstacion.add("5");
        arraySpinnerEstacion.add("6");
        arraySpinnerEstacion.add("7");

        ArrayList<String> arraySpinnerGravedad = new ArrayList<>();
        arraySpinnerGravedad.add("Seleccione una");
        arraySpinnerGravedad.add("Pequeña");
        arraySpinnerGravedad.add("Mediana");
        arraySpinnerGravedad.add("Grande");

        ArrayAdapter<String> adapterArea = new ArrayAdapter<String>(this.getActivity().getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,arraySpinnerArea);
        ArrayAdapter<String> adapterLinea = new ArrayAdapter<String>(this.getActivity().getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,arraySpinnerLinea);
        ArrayAdapter<String> adapterMaquina = new ArrayAdapter<String>(this.getActivity().getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,arraySpinnerMaquina);
        ArrayAdapter<String> adapterEstacion = new ArrayAdapter<String>(this.getActivity().getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,arraySpinnerEstacion);
        ArrayAdapter<String> adapterGravedad = new ArrayAdapter<String>(this.getActivity().getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,arraySpinnerGravedad);

        Area.setAdapter(adapterArea);
        Linea.setAdapter(adapterLinea);
        Maquina.setAdapter(adapterMaquina);
        Estacion.setAdapter(adapterEstacion);
        Gravedad.setAdapter(adapterGravedad);

        btAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String txtArea = Area.getSelectedItem().toString();
                String txtLinea = Linea.getSelectedItem().toString();
                String txtMaquina = Maquina.getSelectedItem().toString();
                String txtEstacion = Estacion.getSelectedItem().toString();
                String txtGravedad = Gravedad.getSelectedItem().toString();
                String txtDescripcion = Descripcion.getText().toString();

                if(txtArea.equals("Seleccione una") || txtLinea.equals("Seleccione una")
                        || txtMaquina.equals("Seleccione una")
                        || txtEstacion.equals("Seleccione una")
                        || txtGravedad.equals("Seleccione una")
                        || txtMaquina.equals("Seleccione una")
                        || txtDescripcion.isEmpty()){
                    Toast.makeText(getActivity(),"Hay un campo vacío. Antes de presionar agregar llene todos los campos."
                            ,Toast.LENGTH_LONG).show();
                }else{
                    //Toast.makeText(getActivity()," INFO:" + txtCodigoEmpleado + txtArea + txtLinea + txtNombreLider + "Lider"+ txtTurno,Toast.LENGTH_LONG).show();

                   // agregarCodigoDefecto(txtArea,txtLinea,txtNombreLider,"Lider",txtTurno);
                }
            }
        });

        return v;
    }

  /*  public void agregarCodigoDefecto(String area, String gravedad, String descripcion, String codigoDefecto,  String maquina){

        Call<String> call = api.saveCodigoDefecto(codigo_empleado,area,linea,nombre,puesto,turno);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getActivity(),"Code: "+response.code(),Toast.LENGTH_LONG).show();
                }

                if(response.body() == null){
                    Toast.makeText(getActivity(),"Hubo un error en la alta",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getActivity(),"Lider Agregado!", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(getActivity(),t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
    */



    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
