package ru.kahn.imitationchibbis.activity;

import android.os.Bundle;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.squareup.picasso.Picasso;
import ru.kahn.imitationchibbis.R;

public class ImageActivity extends AppCompatActivity {

    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        mImageView = findViewById(R.id.image_view);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String imageId = extras.getString("image_id");
            Picasso.with(getApplicationContext())
                    .load(imageId)
                    .fit()
                    .into(mImageView);
        }
    }

}
