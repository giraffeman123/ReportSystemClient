package com.furukawa.reportsystem.reportsystemapp.ui.codigo_defecto;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.furukawa.reportsystem.reportsystemapp.R;
import com.furukawa.reportsystem.reportsystemapp.api.model.CodigoDefecto;
import com.furukawa.reportsystem.reportsystemapp.api.model.Lider;
import com.furukawa.reportsystem.reportsystemapp.ui.adapter.AdapterCodigoDefecto;
import com.furukawa.reportsystem.reportsystemapp.ui.adapter.AdapterLider;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ConsultarCodigoDefectoPorAreaAndMaquina.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ConsultarCodigoDefectoPorAreaAndMaquina#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ConsultarCodigoDefectoPorAreaAndMaquina extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String LISTA_CODIGO_DEFECTO = "lista_codigo";

    // TODO: Rename and change types of parameters
    private List<CodigoDefecto> lista_codigo_defecto;
    private RecyclerView recyclerViewCodigoDefectoConsultaGeneral = null;
    List<CodigoDefecto> lista = new ArrayList<>();
    private AdapterCodigoDefecto mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private OnFragmentInteractionListener mListener;

    public ConsultarCodigoDefectoPorAreaAndMaquina() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ConsultarCodigoDefectoPorAreaAndMaquina.
     */
    // TODO: Rename and change types and number of parameters
    public static ConsultarCodigoDefectoPorAreaAndMaquina newInstance(Serializable lista) {
        ConsultarCodigoDefectoPorAreaAndMaquina fragment = new ConsultarCodigoDefectoPorAreaAndMaquina();
        Bundle args = new Bundle();
        args.putSerializable(LISTA_CODIGO_DEFECTO, lista);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            lista_codigo_defecto = (List<CodigoDefecto>) getArguments().getSerializable(LISTA_CODIGO_DEFECTO);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_consultar_codigo_defecto_por_area_maquina, container, false);
        recyclerViewCodigoDefectoConsultaGeneral = v.findViewById(R.id.recyclerViewCodigoDefectoConsultaGeneral);
        // Inflate the layout for this fragment

        recyclerViewCodigoDefectoConsultaGeneral.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        recyclerViewCodigoDefectoConsultaGeneral.setLayoutManager(mLayoutManager);

        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(recyclerViewCodigoDefectoConsultaGeneral.getContext(),DividerItemDecoration.VERTICAL);
        recyclerViewCodigoDefectoConsultaGeneral.addItemDecoration(mDividerItemDecoration);
        mAdapter = new AdapterCodigoDefecto(lista);

        mAdapter = new AdapterCodigoDefecto(lista_codigo_defecto);
        recyclerViewCodigoDefectoConsultaGeneral.setAdapter(mAdapter);
        // lideresList.setAdapter(new AdapterLider(getApplicationContext(), repos));

        //Toast.makeText(getActivity(),"Code: "+response.code(),Toast.LENGTH_LONG).show();
        //return inflater.inflate(R.layout.fragment_consultar_codigo_defecto_por_area, container, false);
        return v;
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
