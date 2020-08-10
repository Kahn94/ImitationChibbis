package ru.kahn.imitationchibbis.model;

import android.net.Uri;

import java.net.URI;
import java.util.List;

public class ModelRestaurants {

    String Name;
    String Logo;
    int MinCost;
    int DeliveryCost;
    int DeliveryTime;
    String PositiveReviews;
    int ReviewsCount;
    List<ModelSpecializations> Specializations;

    public String getName() {
        return Name;
    }

    public String getLogo() {
        return Logo;
    }

    public int getMinCost() {
        return MinCost;
    }

    public int getDeliveryCost() {
        return DeliveryCost;
    }

    public int getDeliveryTime() {
        return DeliveryTime;
    }

    public String getPositiveReviews() {
        return PositiveReviews;
    }

    public int getReviewsCount() {
        return ReviewsCount;
    }

    public List<ModelSpecializations> getSpecializations() {
        return Specializations;
    }

    public class ModelSpecializations {

        String Name;

        public String getName() {
            return Name;
        }
    }
}

