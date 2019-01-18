package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    Sandwich sandwich;

    @BindView(R.id.also_known_tv)
    TextView tvAlsoKnownAs;
    @BindView(R.id.ingredients_tv)
    TextView tvIngredients;
    @BindView(R.id.origin_tv)
    TextView tvPlaceOfOrigin;
    @BindView(R.id.description_tv)
    TextView tvDescription;
    @BindView(R.id.image_iv)
    ImageView ingredientsIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }
        initialise();
        populateUI();
        Picasso.with(this)
            .load(sandwich.getImage())
            .into(ingredientsIv);

        Picasso.with(this)
            .load(sandwich.getImage())
            .placeholder(R.drawable.image_placeholder)
            .error(R.drawable.image_error)
            .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void initialise() {

    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI() {
        tvAlsoKnownAs.setText(TextUtils.join(", ", sandwich.getAlsoKnownAs()));
        tvIngredients.setText(TextUtils.join(", ", sandwich.getIngredients()));
        tvPlaceOfOrigin.setText(sandwich.getPlaceOfOrigin());
        tvDescription.setText(sandwich.getDescription());
    }
}
