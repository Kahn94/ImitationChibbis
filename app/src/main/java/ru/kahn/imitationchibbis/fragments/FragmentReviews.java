package ru.kahn.imitationchibbis.fragments;

import android.content.Context;
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
import ru.kahn.imitationchibbis.adapters.AdapterFragmentReviews;
import ru.kahn.imitationchibbis.application.AppLoad;
import ru.kahn.imitationchibbis.model.ModelReviews;

public class FragmentReviews extends Fragment {
    private View view;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefresh;
    private AdapterFragmentReviews adapter;

    List<ModelReviews> arrayListReviews = new ArrayList<>();

    @Override
    public void onAttach(Context context) {
        reviewsResponce();
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_main_reviews, null);

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

        adapter = new AdapterFragmentReviews(arrayListReviews);
        recyclerView = view.findViewById(R.id.rv_fragment_reviews);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        swipeRefresh = view.findViewById(R.id.sr_fragment_reviews);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                arrayListReviews.clear();
                reviewsResponce();
                adapter.notifyDataSetChanged();
            }
        });

        return view;
    }

    private void reviewsResponce() {
        Call<List<ModelReviews>> call = AppLoad.getInstance().getApi().reviewsResponce();
        call.enqueue(new Callback<List<ModelReviews>>() {
            @Override
            public void onResponse(Call<List<ModelReviews>> call, Response<List<ModelReviews>> response) {
                if(response.isSuccessful() && response.body() != null) {
                    arrayListReviews.addAll(response.body());
                } else {
                    Log.e("notSuccessful", response.message());
                }
                swipeRefresh.setRefreshing(false);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<ModelReviews>> call, Throwable t) {
                Log.e("ERROR", t.toString());
                swipeRefresh.setRefreshing(false);
            }
        });
    }
}
