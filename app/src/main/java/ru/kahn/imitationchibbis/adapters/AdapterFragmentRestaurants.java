package ru.kahn.imitationchibbis.adapters;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import ru.kahn.imitationchibbis.R;
import ru.kahn.imitationchibbis.model.ModelRestaurants;
import ru.kahn.imitationchibbis.other.CircularTransformation;

public class AdapterFragmentRestaurants extends RecyclerView.Adapter<AdapterFragmentRestaurants.ViewHolder> {

    private List<ModelRestaurants> arrayListRestaurants;

    public AdapterFragmentRestaurants(List<ModelRestaurants> arrayListRestaurants) {
        this.arrayListRestaurants = arrayListRestaurants;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fragment_main_restaurants, parent, false);
        return new ViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ModelRestaurants model = arrayListRestaurants.get(position);

        Picasso.with(holder.itemView.getContext())
            .load(model.getLogo())
            .fit()
            .transform(new CircularTransformation(50))
            .into(holder.item_main_restaurant_image);

        holder.item_main_restaurant_name.setText(model.getName());
        holder.item_main_restaurant_message.setText(getSpecializations(model.getSpecializations()));
        holder.item_main_restaurant_persent.setText(model.getPositiveReviews() +"%");
    }

    @Override
    public int getItemCount() {
        if (arrayListRestaurants == null) {
            return 0;
        }
        return arrayListRestaurants.size();
    }

    private String getSpecializations(List<ModelRestaurants.ModelSpecializations> specializations) {
        String str = "";
        for(ModelRestaurants.ModelSpecializations spec : specializations) {
            str = str +" "+ spec.getName() +" /";
        }
        return str.substring(0, str.length()-1);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView item_main_restaurant_image;
        TextView item_main_restaurant_name;
        TextView item_main_restaurant_message;
        TextView item_main_restaurant_persent;

        ViewHolder(View itemView) {
            super(itemView);
            item_main_restaurant_image = itemView.findViewById(R.id.item_main_restaurant_image);
            item_main_restaurant_name = itemView.findViewById(R.id.item_main_restaurant_name);
            item_main_restaurant_message = itemView.findViewById(R.id.item_main_restaurant_message);
            item_main_restaurant_persent = itemView.findViewById(R.id.item_main_restaurant_persent);
        }
    }
}
