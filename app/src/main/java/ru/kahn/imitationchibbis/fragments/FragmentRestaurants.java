package ru.kahn.imitationchibbis.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.kahn.imitationchibbis.R;
import ru.kahn.imitationchibbis.adapters.AdapterFragmentRestaurants;
import ru.kahn.imitationchibbis.application.AppLoad;
import ru.kahn.imitationchibbis.model.ModelRestaurants;

public class FragmentRestaurants extends Fragment {
    private View view;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefresh;
    private AdapterFragmentRestaurants adapter;
    private EditText inputSearch;

    private List<ModelRestaurants> arrayListRestaurants = new ArrayList<>();
    private List<ModelRestaurants> arrayListRestaurantsArray = new ArrayList<>();

    @Override
    public void onAttach(Context context) {
        restaurantsResponce();
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_main_restaurants, null);
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

        setHasOptionsMenu(true);

        adapter = new AdapterFragmentRestaurants(arrayListRestaurants);
        recyclerView = view.findViewById(R.id.rv_fragment_restaurants);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        swipeRefresh = view.findViewById(R.id.sr_fragment_restaurants);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                arrayListRestaurants.clear();
                restaurantsResponce();
                adapter.notifyDataSetChanged();
            }
        });
        inputSearch = view.findViewById(R.id.et_search);
        inputSearch.addTextChangedListener(textWatcher);

        return view;
    }

    private void restaurantsResponce() {
        Call<List<ModelRestaurants>> call = AppLoad.getInstance().getApi().restaurantsResponse();
        call.enqueue(new Callback<List<ModelRestaurants>>() {
            @Override
            public void onResponse(Call<List<ModelRestaurants>> call, Response<List<ModelRestaurants>> response) {
                if(response.isSuccessful() && response.body() != null) {
                    arrayListRestaurants.addAll(response.body());
                    arrayListRestaurantsArray.addAll(response.body());
                } else {
                    Log.e("notSuccessful", response.message());
                }
                swipeRefresh.setRefreshing(false);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<ModelRestaurants>> call, Throwable t) {
                Log.e("ERROR", t.toString());
                swipeRefresh.setRefreshing(false);
            }
        });
    }

    private boolean isSearchArray(ModelRestaurants item, String searchText) {
        boolean result = false;
        String[] name = item.getName().split(" ");
        for (String s : name) {
            result = result || s.regionMatches(true, 0, searchText, 0, searchText.length());
        }
        return result;
    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @SuppressLint("NewApi")
        @Override
        public void onTextChanged(CharSequence newText, int start, int before, int count) {
            arrayListRestaurants.clear();
            for(ModelRestaurants item : arrayListRestaurantsArray) {
                if (isSearchArray(item, String.valueOf(newText))) {
                    arrayListRestaurants.add(item);
                }
                Objects.requireNonNull(recyclerView.getAdapter()).notifyDataSetChanged();
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };
}
