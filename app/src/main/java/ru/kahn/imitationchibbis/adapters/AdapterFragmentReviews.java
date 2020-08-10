package ru.kahn.imitationchibbis.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ru.kahn.imitationchibbis.R;
import ru.kahn.imitationchibbis.model.ModelReviews;

public class AdapterFragmentReviews extends RecyclerView.Adapter<AdapterFragmentReviews.ViewHolder> {

    List<ModelReviews> arrayListReviews;

    public AdapterFragmentReviews(List<ModelReviews> arrayListReviews) {
        this.arrayListReviews = arrayListReviews;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fragment_main_reviews, parent, false);
        return new AdapterFragmentReviews.ViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ModelReviews model = arrayListReviews.get(position);

        if(model.isPositive()) {
            holder.item_main_reviews_image.setBackgroundResource(R.drawable.ic_item_like);
        } else {
            holder.item_main_reviews_image.setBackgroundResource(R.drawable.ic_item_dislike);
        }
        holder.item_main_reviews_name.setText(model.getUserFIO() +" о ресторане "+ model.getRestaurantName());
        holder.item_main_reviews_message.setText(model.getMessage());
        holder.item_main_reviews_time.setText(model.getDateAdded());
    }

    @Override
    public int getItemCount() {
        if (arrayListReviews == null) {
            return 0;
        }
        return arrayListReviews.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView item_main_reviews_image;
        TextView item_main_reviews_name;
        TextView item_main_reviews_message;
        TextView item_main_reviews_time;

        ViewHolder(View itemView) {
            super(itemView);
            item_main_reviews_image = itemView.findViewById(R.id.item_main_reviews_image);
            item_main_reviews_name = itemView.findViewById(R.id.item_main_reviews_name);
            item_main_reviews_message = itemView.findViewById(R.id.item_main_reviews_message);
            item_main_reviews_time = itemView.findViewById(R.id.item_main_reviews_time);
        }
    }
}
