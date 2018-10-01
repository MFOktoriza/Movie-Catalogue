package com.vedici.moviecatalogue.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.vedici.moviecatalogue.PreferenceHelper;
import com.vedici.moviecatalogue.R;
import com.vedici.moviecatalogue.model.Movie;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieBackdropActivity extends AppCompatActivity {
	public static String EXTRA_MOVIE = "extra_movie";

	@BindView(R.id.img_movie_backdrop)
	ImageView movieBackdrop;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_movie_backdrop);
		ButterKnife.bind(this);
		this.getMovieBackdrop();
	}

	public void getMovieBackdrop() {
		Movie movie = getIntent().getParcelableExtra(EXTRA_MOVIE);
		Glide.with(this)
				.load(PreferenceHelper.getImageUrl() + movie.getBackdrop_path())
				.apply(new RequestOptions()
						.placeholder(R.drawable.ic_landscape_black_24dp)
						.fitCenter())
				.into(movieBackdrop);
	}
}
