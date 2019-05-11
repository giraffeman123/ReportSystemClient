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
import android.widget.TextView;
import android.widget.Toast;

import com.furukawa.reportsystem.reportsystemapp.R;
import com.furukawa.reportsystem.reportsystemapp.api.model.CodigoDefecto;
import com.furukawa.reportsystem.reportsystemapp.api.service.ApiUtils;
import com.furukawa.reportsystem.reportsystemapp.api.service.ReportSystemInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ModificarCodigoDefecto.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ModificarCodigoDefecto#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ModificarCodigoDefecto extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String CODIGO_DEFECTO = "codigo_defecto";
    private static final String AREA = "area";
    private static final String MAQUINA = "maquina";
    private static final String GRAVEDAD = "gravedad";
    private static final String DESCRIPCION = "descripcion";

    // TODO: Rename and change types of parameters
    private CodigoDefecto codigoDefecto;
    /*
    private ArrayAdapter<String> adapterArea;
    private ArrayAdapter<String> adapterMaquina;
    */
    private ArrayAdapter<String> adapterGravedad;
    private ReportSystemInterface api;

    private OnFragmentInteractionListener mListener;

    public ModificarCodigoDefecto() {
        // Required empty public constructor
    }

    /**
     *
     * @param codigo
     * @param area
     * @param maquina
     * @param gravedad
     * @param descripcion
     * @return
     */
    // TODO: Rename and change types and number of parameters
    public static ModificarCodigoDefecto newInstance(String codigo, String area, String maquina,
                                                     String gravedad, String descripcion) {
        ModificarCodigoDefecto fragment = new ModificarCodigoDefecto();
        Bundle args = new Bundle();
        args.putString(CODIGO_DEFECTO, codigo);
        args.putString(AREA, area);
        args.putString(MAQUINA, maquina);
        args.putString(GRAVEDAD, gravedad);
        args.putString(DESCRIPCION, descripcion);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            codigoDefecto = new CodigoDefecto(getArguments().getString(CODIGO_DEFECTO)
                    ,getArguments().getString(AREA),getArguments().getString(MAQUINA)
                    ,getArguments().getString(GRAVEDAD)
                    ,getArguments().getString(DESCRIPCION));
        }
        api = ApiUtils.getReportSystemService();
        initializeSpinners();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_modificar_codigo_defecto, container, false);

        final Button btAceptar = v.findViewById(R.id.btAceptar);
        final TextView edtxtCodigo = v.findViewById(R.id.edtxtCodigo);
        final TextView edtxtArea = v.findViewById(R.id.edtxtArea);
        final TextView edtxtMaquina = v.findViewById(R.id.edtxtMaquina);

        final Spinner spGravedad = v.findViewById(R.id.spGravedad);
        spGravedad.setAdapter(adapterGravedad);

        final EditText edtxtDescripcion = v.findViewById(R.id.edtxtDescripcion);

        // se añaden todos los valores de codigo defecto a los componentes View
        edtxtCodigo.setText(codigoDefecto.getCodigoDefecto());
        edtxtArea.setText(codigoDefecto.getArea());
        edtxtMaquina.setText(codigoDefecto.getMaquina());

        int spPosGravedad = adapterGravedad.getPosition(codigoDefecto.getGravedad());
        spGravedad.setSelection(spPosGravedad);

        edtxtDescripcion.setText(codigoDefecto.getDescripcion());

        btAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edtxtDescripcion.getText().toString().isEmpty() || spGravedad.getSelectedItem().toString().equals("Seleccione una")) {
                    Toast.makeText(getActivity(), "Hay un campo vacío. Antes de presionar modificar llene todos los campos."
                            , Toast.LENGTH_LONG).show();
                }else {
                    modificarCodigoDefecto(edtxtCodigo.getText().toString(),spGravedad.getSelectedItem().toString()
                            ,edtxtDescripcion.getText().toString());
                }
            }
        });
        return v;
    }

    public void modificarCodigoDefecto(String codigo, String gravedad, String descripcion){
        Call<String> call = api.uptadeCodigoDefecto(codigo,gravedad,descripcion);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getActivity(),"Code: "+response.code(),Toast.LENGTH_LONG).show();
                }

                if(response.body() == null){
                    Toast.makeText(getActivity(),"Hubo un error con la modificación",Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(getActivity(),"Código Defecto Modificado.",Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(getActivity(),t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

    public void initializeSpinners(){
        /*
        ArrayList<String> arraySpinnerArea = new ArrayList<String>();
        arraySpinnerArea.add("Seleccione una");
        arraySpinnerArea.add("Fusebox");
        arraySpinnerArea.add("SRC");
        arraySpinnerArea.add("Ensamblaje");
        arraySpinnerArea.add("Produccion");
        ArrayList<String> arraySpinnerMaquina = new ArrayList<String>();
        arraySpinnerMaquina.add("Seleccione una");
        arraySpinnerMaquina.add("Basbar Press");
        arraySpinnerMaquina.add("Bara Busbar Injection");
        arraySpinnerMaquina.add("Greaser Machine");
        arraySpinnerMaquina.add("Fuse Press");
        arraySpinnerMaquina.add("Multi Function Press");
        arraySpinnerMaquina.add("Final Tester");
        */
        ArrayList<String> arraySpinnerGravedad = new ArrayList<String>();
        arraySpinnerGravedad.add("Seleccione una");
        arraySpinnerGravedad.add("Bajo");
        arraySpinnerGravedad.add("Media");
        arraySpinnerGravedad.add("Alto");

        /*
        adapterArea = new ArrayAdapter<String>(this.getActivity().getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,arraySpinnerArea);
        adapterMaquina = new ArrayAdapter<String>(this.getActivity().getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,arraySpinnerMaquina);
        */

        adapterGravedad = new ArrayAdapter<String>(this.getActivity().getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,arraySpinnerGravedad);
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
