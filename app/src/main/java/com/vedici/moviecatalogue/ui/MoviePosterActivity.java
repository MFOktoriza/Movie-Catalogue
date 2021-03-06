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

public class MoviePosterActivity extends AppCompatActivity {
	public static String EXTRA_MOVIE = "extra_movie";

	@BindView(R.id.img_movie_poster)
	ImageView moviePoster;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_movie_poster);
		ButterKnife.bind(this);
		this.getMoviePoster();
	}

	public void getMoviePoster() {
		Movie movie = getIntent().getParcelableExtra(EXTRA_MOVIE);
		Glide.with(this)
				.load(PreferenceHelper.getImageUrl() + movie.getPoster_path())
				.apply(new RequestOptions()
						.placeholder(R.drawable.ic_landscape_black_24dp)
						.centerCrop())
				.into(moviePoster);
	}
}
