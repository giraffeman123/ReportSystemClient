package com.furukawa.reportsystem.reportsystemapp.ui.lider;

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
import com.furukawa.reportsystem.reportsystemapp.api.model.Empleado;
import com.furukawa.reportsystem.reportsystemapp.api.model.Lider;
import com.furukawa.reportsystem.reportsystemapp.api.service.ApiUtils;
import com.furukawa.reportsystem.reportsystemapp.api.service.ReportSystemInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ModificarLider.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ModificarLider#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ModificarLider extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String CODIGO_EMPLEADO = "codigo_empleado";
    private static final String AREA = "area";
    private static final String LINEA = "linea";
    private static final String NOMBRE = "nombre";
    private static final String PUESTO = "puesto";
    private static final String TURNO = "turno";

    // TODO: Rename and change types of parameters
    private Lider lider;
    private ArrayAdapter<String> adapterLinea;
    private ArrayAdapter<String> adapterTurno;
    private ArrayAdapter<String> adapterArea;
    private ReportSystemInterface api;

    private OnFragmentInteractionListener mListener;

    public ModificarLider() {
        // Required empty public constructor
    }

    /**
     *
     * @param codigo_empleado
     * @param area
     * @param linea
     * @param nombre
     * @param puesto
     * @param turno
     * @return
     */
    // TODO: Rename and change types and number of parameters
    public static ModificarLider newInstance(String codigo_empleado, String area, String linea, String nombre,
                                             String puesto, String turno) {
        ModificarLider fragment = new ModificarLider();
        Bundle args = new Bundle();
        args.putString(CODIGO_EMPLEADO, codigo_empleado);
        args.putString(AREA, area);
        args.putString(LINEA, linea);
        args.putString(NOMBRE, nombre);
        args.putString(PUESTO, puesto);
        args.putString(TURNO, turno);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            lider = new Lider(getArguments().getString(CODIGO_EMPLEADO),getArguments().getString(AREA),
                    Integer.parseInt(getArguments().getString(LINEA)));
            Empleado empleado = new Empleado(getArguments().getString(CODIGO_EMPLEADO),
                    getArguments().getString(NOMBRE),getArguments().getString(PUESTO),
                    getArguments().getString(TURNO));
            lider.setEmpleado(empleado);
        }
        api = ApiUtils.getReportSystemService();
        initializeSpinners();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_modificar_lider, container, false);
        final EditText edtxtCodigo = v.findViewById(R.id.edtxtCodigoDeEmpleado);
        final EditText edtxtNombre = v.findViewById(R.id.edtxtNombre);

        final Spinner spLinea = v.findViewById(R.id.spLinea);
        spLinea.setAdapter(adapterLinea);

        final Spinner spArea = v.findViewById(R.id.spArea);
        spArea.setAdapter(adapterArea);

        final Spinner spTurno = v.findViewById(R.id.spTurno);
        spTurno.setAdapter(adapterTurno);

        final Button btAceptar = v.findViewById(R.id.btAceptar);

        edtxtCodigo.setText(lider.getCodigoEmpleado());
        edtxtNombre.setText(lider.getEmpleado().getNombre());

        int spPosLinea = adapterLinea.getPosition(String.valueOf(lider.getLinea()));
        spLinea.setSelection(spPosLinea);

        int spPosArea = adapterArea.getPosition(lider.getArea());
        spArea.setSelection(spPosArea);

        int spPosTurno = adapterTurno.getPosition(lider.getEmpleado().getTurno());
        spTurno.setSelection(spPosTurno);

        btAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edtxtCodigo.getText().toString().isEmpty() || edtxtNombre.getText().toString().isEmpty()
                        || spLinea.getSelectedItem().toString().equals("Seleccione una")
                        || spArea.getSelectedItem().toString().equals("Seleccione una") ||
                        spTurno.getSelectedItem().toString().equals("Seleccione una")) {
                    Toast.makeText(getActivity(), "Hay un campo vacío. Antes de presionar agregar llene todos los campos."
                            , Toast.LENGTH_LONG).show();
                }else {
                    modificarLider(edtxtCodigo.getText().toString(), spArea.getSelectedItem().toString(),
                            spLinea.getSelectedItem().toString(), edtxtNombre.getText().toString(), "lider",
                            spTurno.getSelectedItem().toString());
                }
            }
        });
        return v;
    }

    public void modificarLider(String codigo_empleado, String area, String linea, String nombre,
                               String puesto, String turno){
        Call<String> call = api.updateLider(codigo_empleado,area,linea,nombre,puesto,turno);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getActivity(),"Code: "+response.code(),Toast.LENGTH_LONG).show();
                }
                if(response.body() == null){
                    Toast.makeText(getActivity(),"Hubo un error en la modificación",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getActivity(),"Lider Modificado", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(getActivity(),t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

    public void initializeSpinners(){
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

        adapterLinea = new ArrayAdapter<String>(this.getActivity().getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,arraySpinnerLinea);
        adapterArea = new ArrayAdapter<String>(this.getActivity().getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,arraySpinnerArea);
        adapterTurno = new ArrayAdapter<String>(this.getActivity().getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,arraySpinnerTurno);
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
