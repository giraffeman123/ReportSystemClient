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
 * Use the {@link ModificarLider#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ModificarLider extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Lider lider;
    private ReportSystemInterface api;

    public ModificarLider() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ModificarLider.
     */
    // TODO: Rename and change types and number of parameters
    public static ModificarLider newInstance(String param1, String param2) {
        ModificarLider fragment = new ModificarLider();
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
        View v = inflater.inflate(R.layout.fragment_modificar_lider, container, false);
        final EditText codigoEdTxt = v.findViewById(R.id.edtxtIngreseCodigo);
        final Button btAceptar = v.findViewById(R.id.btAceptar);
        btAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modificarLider(codigoEdTxt.getText().toString());
            }
        });
        return v;
    }

    public void modificarLider(String codigoEmpleado){
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
