package com.furukawa.reportsystem.reportsystemapp.ui.lider;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
 * {@link AgregarLider.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AgregarLider#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AgregarLider extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String selectedLineaOption;
    private String selectedAreaOption;
    private String selectedTurnoOption;

    private ReportSystemInterface api;

    private OnFragmentInteractionListener mListener;

    public AgregarLider() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AgregarLider.
     */
    // TODO: Rename and change types and number of parameters
    public static AgregarLider newInstance(String param1, String param2) {
        AgregarLider fragment = new AgregarLider();
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
        View v = inflater.inflate(R.layout.fragment_agregar_lider, container, false);


        final Spinner Linea = (Spinner) v.findViewById(R.id.spLinea);
        final Spinner Area = (Spinner) v.findViewById(R.id.spArea);
        final Spinner Turno = (Spinner) v.findViewById(R.id.spTurno);
        final EditText CodigoEmpleado = (EditText) v.findViewById(R.id.edtxtCodigoDeEmpleado);
        final EditText Nombre = (EditText) v.findViewById(R.id.edtxtNombre);
        final Button btAceptar = (Button) v.findViewById(R.id.btAceptar);

        ArrayList<String> arraySpinnerLinea = new ArrayList<>();
        arraySpinnerLinea.add("Seleccione una");
        arraySpinnerLinea.add("1");
        arraySpinnerLinea.add("2");
        arraySpinnerLinea.add("3");
        arraySpinnerLinea.add("4");
        arraySpinnerLinea.add("5");
        arraySpinnerLinea.add("6");
        arraySpinnerLinea.add("7");

        ArrayList<String> arraySpinnerArea = new ArrayList<>();
        arraySpinnerArea.add("Seleccione una");
        arraySpinnerArea.add("Fusebox");
        arraySpinnerArea.add("SRC");
        arraySpinnerArea.add("Ensamblaje");
        arraySpinnerArea.add("Produccion");

        ArrayList<String> arraySpinnerTurno = new ArrayList<>();
        arraySpinnerTurno.add("Seleccione una");
        arraySpinnerTurno.add("Matutino");
        arraySpinnerTurno.add("Intermedio");
        arraySpinnerTurno.add("Vespertino");


        ArrayAdapter<String> adapterLinea = new ArrayAdapter<String>(this.getActivity().getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,arraySpinnerLinea);
        ArrayAdapter<String> adapterArea = new ArrayAdapter<String>(this.getActivity().getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,arraySpinnerArea);
        ArrayAdapter<String> adapterTurno = new ArrayAdapter<String>(this.getActivity().getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,arraySpinnerTurno);

        Linea.setAdapter(adapterLinea);
        Area.setAdapter(adapterArea);
        Turno.setAdapter(adapterTurno);


        Linea.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedLineaOption = Linea.getSelectedItem().toString();
                selectedAreaOption = Area.getSelectedItem().toString();
                selectedTurnoOption = Turno.getSelectedItem().toString();
                /*Toast.makeText(getActivity().getApplicationContext(),selectedLineaOption+
                        selectedAreaOption+selectedTurnoOption, Toast.LENGTH_LONG).show();
                        */
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String txtCodigoEmpleado = CodigoEmpleado.getText().toString();
                String txtNombreLider = Nombre.getText().toString();
                String txtLinea = Linea.getSelectedItem().toString();
                String txtArea = Area.getSelectedItem().toString();
                String txtTurno = Turno.getSelectedItem().toString();

                agregarLider(txtCodigoEmpleado,txtArea,txtLinea,txtNombreLider,"Lider",txtTurno);
            }
        });
        // Inflate the layout for this fragment
        return v;
    }


    public void agregarLider(String codigo_empleado, String area, String linea, String nombre, String puesto, String turno){

        Call<String> call = api.saveLider(codigo_empleado,area,linea,nombre,puesto,turno);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getActivity(),"Code: "+response.code(),Toast.LENGTH_LONG).show();
                }
                Toast.makeText(getActivity(),"Lider Agregado!", Toast.LENGTH_LONG).show();
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(getActivity(),t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

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
