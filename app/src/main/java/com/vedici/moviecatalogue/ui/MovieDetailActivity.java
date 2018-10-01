package com.vedici.moviecatalogue.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.vedici.moviecatalogue.PreferenceHelper;
import com.vedici.moviecatalogue.R;
import com.vedici.moviecatalogue.model.Movie;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MovieDetailActivity extends AppCompatActivity {
	public static String EXTRA_MOVIE = "extra_movie";

	@BindView(R.id.img_movie_backdrop)
	ImageView movieBackdrop;
	@BindView(R.id.img_movie_poster)
	ImageView moviePoster;
	@BindView(R.id.tv_movie_title)
	TextView movieTitle;
	@BindView(R.id.tv_movie_vote_average)
	TextView movieVoteAverage;
	@BindView(R.id.tv_movie_vote_count)
	TextView movieVoteCount;
	@BindView(R.id.tv_movie_original_language)
	TextView movieOriginalLanguage;
	@BindView(R.id.tv_movie_release_date)
	TextView movieReleaseDate;
	@BindView(R.id.tv_movie_overview)
	TextView movieOverview;

	@OnClick(R.id.img_movie_poster)
	public void onClickMoviePoster() {
		Movie movie = getIntent().getParcelableExtra(EXTRA_MOVIE);
		Intent movieViewPosterIntent = new Intent(this, MoviePosterActivity.class);
		movieViewPosterIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		movieViewPosterIntent.putExtra(MovieDetailActivity.EXTRA_MOVIE, movie);
		startActivity(movieViewPosterIntent);
	}

	@OnClick(R.id.img_movie_backdrop)
	public void onClickMovieBackdrop() {
		Movie movie = getIntent().getParcelableExtra(EXTRA_MOVIE);
		Intent movieViewBackdropIntent = new Intent(this, MovieBackdropActivity.class);
		movieViewBackdropIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		movieViewBackdropIntent.putExtra(MovieDetailActivity.EXTRA_MOVIE, movie);
		startActivity(movieViewBackdropIntent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_movie_detail);
		ButterKnife.bind(this);
		this.getMovieDetail();
	}

	public void getMovieDetail() {
		Movie movie = getIntent().getParcelableExtra(EXTRA_MOVIE);
		Glide.with(this)
				.load(PreferenceHelper.getImageUrl() + movie.getBackdrop_path())
				.apply(new RequestOptions()
						.placeholder(R.drawable.ic_landscape_black_24dp)
						.centerCrop())
				.into(movieBackdrop);
		Glide.with(this)
				.load(PreferenceHelper.getImageUrl() + movie.getPoster_path())
				.apply(new RequestOptions()
						.placeholder(R.drawable.ic_landscape_black_24dp)
						.override(150, 200)
						.fitCenter())
				.into(moviePoster);
		movieTitle.setText(movie.getTitle());
		movieVoteAverage.setText(String.format("%f", movie.getVote_average()));
		movieVoteCount.setText(String.format("%d", movie.getVote_count()));
		movieOriginalLanguage.setText(movie.getOriginal_language());
		movieReleaseDate.setText(movie.getRelease_date());
		movieOverview.setText(movie.getOverview());
	}
}
