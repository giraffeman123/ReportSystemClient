package com.furukawa.reportsystem.reportsystemapp.ui.codigo_defecto;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.furukawa.reportsystem.reportsystemapp.R;
import com.furukawa.reportsystem.reportsystemapp.api.model.CodigoDefecto;
import com.furukawa.reportsystem.reportsystemapp.api.service.ApiUtils;
import com.furukawa.reportsystem.reportsystemapp.api.service.ReportSystemInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BuscarCodigoDefecto.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class BuscarCodigoDefecto extends Fragment {

    private CodigoDefecto codigoDefecto;
    private ReportSystemInterface api;
    private OnSendingCodigoDefectoListener listener;

    public BuscarCodigoDefecto() {
        // Required empty public constructor
    }

    public interface OnSendingCodigoDefectoListener{
        void OnInputBuscarCodigoDefectoSent(CodigoDefecto codigoDefecto);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        api = ApiUtils.getReportSystemService();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_buscar_codigo_defecto, container, false);
        final EditText codigoEdTxt = v.findViewById(R.id.edtxrIngresarCodigo);
        final Button btAceptar = v.findViewById(R.id.btAceptar);
        btAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buscarCodigoDefecto(codigoEdTxt.getText().toString());
            }
        });
        return v;
    }

    public void buscarCodigoDefecto(final String codigo){
        if(!codigo.isEmpty()){
            Call<CodigoDefecto> call = api.getCodigoDefectoByCodigo(codigo);
            call.enqueue(new Callback<CodigoDefecto>() {
                @Override
                public void onResponse(Call<CodigoDefecto> call, Response<CodigoDefecto> response) {
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
                public void onFailure(Call<CodigoDefecto> call, Throwable t) {
                    Toast.makeText(getActivity(),t.getMessage(),Toast.LENGTH_LONG).show();
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
