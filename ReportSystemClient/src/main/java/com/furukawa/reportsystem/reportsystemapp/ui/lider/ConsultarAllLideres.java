package com.furukawa.reportsystem.reportsystemapp.ui.lider;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.furukawa.reportsystem.reportsystemapp.R;
import com.furukawa.reportsystem.reportsystemapp.api.model.Lider;
import com.furukawa.reportsystem.reportsystemapp.api.service.ApiUtils;
import com.furukawa.reportsystem.reportsystemapp.api.service.ReportSystemInterface;
import com.furukawa.reportsystem.reportsystemapp.ui.adapter.LiderAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ConsultarAllLideres.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ConsultarAllLideres#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ConsultarAllLideres extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ListView listView;

    public ConsultarAllLideres() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ConsultarAllLideres.
     */
    // TODO: Rename and change types and number of parameters
    public static ConsultarAllLideres newInstance(String param1, String param2) {
        ConsultarAllLideres fragment = new ConsultarAllLideres();
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
        getAllLideres();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_consultar_all_lideres, container, false);
        listView = (ListView) v.findViewById(R.id.pagination_list);
        return v;
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
                listView.setAdapter(new LiderAdapter(getContext(), repos));
                Toast.makeText(getActivity(),"Code: "+response.code(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<List<Lider>> call, Throwable t) {
                Toast.makeText(getActivity(), "Error: "+t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
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
