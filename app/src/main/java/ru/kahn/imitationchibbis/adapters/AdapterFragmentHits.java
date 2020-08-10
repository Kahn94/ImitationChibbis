package ru.kahn.imitationchibbis.adapters;

import android.annotation.SuppressLint;
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
import ru.kahn.imitationchibbis.model.ModelHits;
import ru.kahn.imitationchibbis.other.CircularTransformation;

public class AdapterFragmentHits extends RecyclerView.Adapter<AdapterFragmentHits.ViewHolder> {

    List<ModelHits> arrayListHits;
    private LongClickListener click;

    public interface LongClickListener {
        void onClickImage(String imagePath);
    }

    public AdapterFragmentHits(List<ModelHits> arrayListHits, LongClickListener click) {
        this.click = click;
        this.arrayListHits = arrayListHits;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fragment_main_hits, parent, false);
        return new AdapterFragmentHits.ViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ModelHits model = arrayListHits.get(position);
        final String imagePath = model.getProductImage();

        Picasso.with(holder.itemView.getContext())
                .load(imagePath)
                .transform(new CircularTransformation(50))
                .fit()
                .into(holder.item_main_hits_image_logo);
        holder.item_main_hits_name.setText(model.getProductName());

        holder.item_main_hits_image_logo.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                click.onClickImage(imagePath);
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        if (arrayListHits == null) {
            return 0;
        }
        return arrayListHits.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView item_main_hits_image_logo;
        TextView item_main_hits_name;

        ViewHolder(View itemView) {
            super(itemView);
            item_main_hits_image_logo = itemView.findViewById(R.id.item_main_hits_image_logo);
            item_main_hits_name = itemView.findViewById(R.id.item_main_hits_name);
        }
    }
}
