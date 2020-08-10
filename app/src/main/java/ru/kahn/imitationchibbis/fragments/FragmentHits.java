package ru.kahn.imitationchibbis.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.kahn.imitationchibbis.R;
import ru.kahn.imitationchibbis.activity.ImageActivity;
import ru.kahn.imitationchibbis.adapters.AdapterFragmentHits;
import ru.kahn.imitationchibbis.application.AppLoad;
import ru.kahn.imitationchibbis.model.ModelHits;

public class FragmentHits extends Fragment implements AdapterFragmentHits.LongClickListener {

    private View view;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefresh;
    private AdapterFragmentHits adapter;

    List<ModelHits> arrayListHits = new ArrayList<>();

    @Override
    public void onAttach(Context context) {
        hitsResponce();
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_main_hits, null);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext()) {
            @Override
            public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
                try {
                    super.onLayoutChildren(recycler, state);
                } catch (Exception e) {
                    Log.e("onLayoutChildren :", e.toString());
                }
            }
        };
        adapter = new AdapterFragmentHits(arrayListHits, this);
        recyclerView = view.findViewById(R.id.rv_fragment_hits);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        swipeRefresh = view.findViewById(R.id.sr_fragment_hits);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                arrayListHits.clear();
                hitsResponce();
                adapter.notifyDataSetChanged();
            }
        });

        return view;
    }

    private void hitsResponce() {
        Call<List<ModelHits>> call = AppLoad.getInstance().getApi().hitsResponse();
        call.enqueue(new Callback<List<ModelHits>>() {
            @Override
            public void onResponse(Call<List<ModelHits>> call, Response<List<ModelHits>> response) {
                if(response.isSuccessful() && response.body() != null) {
                    arrayListHits.addAll(response.body());
                } else {
                    Log.e("notSuccessful", response.message());
                }
                swipeRefresh.setRefreshing(false);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<ModelHits>> call, Throwable t) {
                Log.e("ERROR", t.toString());
                swipeRefresh.setRefreshing(false);
            }
        });
    }

    @Override
    public void onClickImage(String imagePath) {
        Intent intent = new Intent(getContext(), ImageActivity.class);
        intent.putExtra("image_id", imagePath);
        startActivity(intent);
    }
}
