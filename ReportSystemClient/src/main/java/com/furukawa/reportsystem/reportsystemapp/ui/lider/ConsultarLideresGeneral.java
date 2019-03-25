package com.furukawa.reportsystem.reportsystemapp.ui.lider;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.furukawa.reportsystem.reportsystemapp.R;
import com.furukawa.reportsystem.reportsystemapp.api.model.Lider;
import com.furukawa.reportsystem.reportsystemapp.api.service.ApiUtils;
import com.furukawa.reportsystem.reportsystemapp.api.service.ReportSystemInterface;
import com.furukawa.reportsystem.reportsystemapp.ui.adapter.AdapterLider;
import com.furukawa.reportsystem.reportsystemapp.ui.adapter.LiderAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConsultarLideresGeneral extends Fragment {

    List<Lider> lideresList = new ArrayList<>();
    TextView txt;
    private RecyclerView recyclerViewLideres = null;
    private AdapterLider mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public static ConsultarLideresGeneral newInstance(String param1, String param2) {
        ConsultarLideresGeneral fragment = new ConsultarLideresGeneral();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_consultar_lideres_general, container, false);
        recyclerViewLideres = v.findViewById(R.id.recyclerViewLideresConsultaGeneral);
        getAllLideres();
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.fragment_consultar_lideres_general);

        //recyclerViewLideres = (RecyclerView) findViewById(R.id.recyclerViewLideresConsultaGeneral);
        /*
        getAllLideres();

        recyclerViewLideres.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        recyclerViewLideres.setLayoutManager(mLayoutManager);

        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(recyclerViewLideres.getContext(),DividerItemDecoration.VERTICAL);
        recyclerViewLideres.addItemDecoration(mDividerItemDecoration);
        mAdapter = new AdapterLider(lideresList);
 */
    }

    public void getAllLideres(){

        ReportSystemInterface api = ApiUtils.getReportSystemService();
        Call<List<Lider>> call = api.getAllLideres();
        call.enqueue(new Callback<List<Lider>>() {

            @Override
            public void onResponse(Call<List<Lider>> call, Response<List<Lider>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getActivity(),"Code: "+ response.code(), Toast.LENGTH_LONG).show();
                }

                List<Lider> repos = response.body();
                recyclerViewLideres.setHasFixedSize(true);
                mLayoutManager = new LinearLayoutManager(getContext());
                recyclerViewLideres.setLayoutManager(mLayoutManager);

                DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(recyclerViewLideres.getContext(),DividerItemDecoration.VERTICAL);
                recyclerViewLideres.addItemDecoration(mDividerItemDecoration);
                mAdapter = new AdapterLider(lideresList);

                mAdapter = new AdapterLider(repos);
                recyclerViewLideres.setAdapter(mAdapter);
               // lideresList.setAdapter(new AdapterLider(getApplicationContext(), repos));

                Toast.makeText(getActivity(),"Code: "+response.code(),Toast.LENGTH_LONG).show();

                }

            @Override
            public void onFailure(Call<List<Lider>> call, Throwable t) {
                Toast.makeText(getActivity(), "Error: "+t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
