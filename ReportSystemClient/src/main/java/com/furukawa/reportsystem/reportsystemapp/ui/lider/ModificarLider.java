package com.furukawa.reportsystem.reportsystemapp.ui.lider;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.furukawa.reportsystem.reportsystemapp.R;
import com.furukawa.reportsystem.reportsystemapp.api.model.Lider;
import com.furukawa.reportsystem.reportsystemapp.api.service.ApiUtils;
import com.furukawa.reportsystem.reportsystemapp.api.service.ReportSystemInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ModificarLider.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class ModificarLider extends Fragment {

    private Lider lider;
    private ReportSystemInterface api;
    private OnSendingLiderListener listener;

    public ModificarLider() {
        // Required empty public constructor
    }

    public interface OnSendingLiderListener{
        void onInputBuscarLiderSent(Lider lider);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        api = ApiUtils.getReportSystemService();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_modificar_lider, container, false);
        final EditText codigoEdTxt = v.findViewById(R.id.edtxtIngreseCodigo);
        final Button btAceptar = v.findViewById(R.id.btAceptar);
        btAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buscarLider(codigoEdTxt.getText().toString());
            }
        });
        return v;
    }

    public void buscarLider(String codigoEmpleado){
        if(!codigoEmpleado.isEmpty()){
            Call<Lider> call = api.getLiderByCodigoEmpleado(codigoEmpleado);
            call.enqueue(new Callback<Lider>() {
                @Override
                public void onResponse(Call<Lider> call, Response<Lider> response) {
                    if(!response.isSuccessful()){
                        Toast.makeText(getActivity(),"Code: "+response.code(),Toast.LENGTH_LONG).show();
                    }

                    if(response.body() != null){
                        lider = response.body();
                        listener.onInputBuscarLiderSent(lider);
                        Toast.makeText(getActivity(),"Lider Encontrado",Toast.LENGTH_LONG).show();
                    }else
                        Toast.makeText(getActivity(),"Lider no encontrado",Toast.LENGTH_LONG).show();
                }

                @Override
                public void onFailure(Call<Lider> call, Throwable t) {
                    Toast.makeText(getActivity(),t.getMessage(),Toast.LENGTH_LONG).show();
                }
            });
        }else
            Toast.makeText(getActivity(),"Ingrese un codigo a buscar",Toast.LENGTH_LONG).show();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof OnSendingLiderListener){
            listener = (OnSendingLiderListener) context;
        }else {
            throw new RuntimeException(context.toString()+" must implement OnSendingLiderListener");
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
