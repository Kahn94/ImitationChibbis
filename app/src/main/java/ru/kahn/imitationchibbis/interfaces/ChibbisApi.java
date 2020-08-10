package ru.kahn.imitationchibbis.interfaces;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import ru.kahn.imitationchibbis.model.ModelHits;
import ru.kahn.imitationchibbis.model.ModelRestaurants;
import ru.kahn.imitationchibbis.model.ModelReviews;

public interface ChibbisApi {

    @GET("restaurants")
    Call<List<ModelRestaurants>> restaurantsResponse();

    @GET("hits")
    Call<List<ModelHits>> hitsResponse();

    @GET("reviews")
    Call<List<ModelReviews>> reviewsResponce();
}
