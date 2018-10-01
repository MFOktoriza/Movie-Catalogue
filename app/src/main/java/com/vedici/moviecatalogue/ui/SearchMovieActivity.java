package com.vedici.moviecatalogue.ui;

import android.content.Intent;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.vedici.moviecatalogue.BuildConfig;
import com.vedici.moviecatalogue.PreferenceHelper;
import com.vedici.moviecatalogue.R;
import com.vedici.moviecatalogue.adapter.CardViewMovieAdapter;
import com.vedici.moviecatalogue.api.MovieApiClientBuilder;
import com.vedici.moviecatalogue.api.MovieApiInterface;
import com.vedici.moviecatalogue.model.Movie;
import com.vedici.moviecatalogue.model.MovieResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchMovieActivity extends AppCompatActivity {
	private List<Movie> mMovieList;
	private static final String TAG = SearchMovieActivity.class.getSimpleName();

	@Nullable
	@BindView(R.id.rv_search_movie)
	RecyclerView rvSearchMovie;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_movie);
		ButterKnife.bind(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_option_search, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.change_language_menu:
				Intent changeLanguageIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
				startActivity(changeLanguageIntent);
				break;
			case R.id.sv_movie:
				SearchView svMovie = item.getActionView().findViewById(R.id.sv_movie);
				svMovie.setQueryHint(getResources().getString(R.string.search_movie));
				svMovie.setSubmitButtonEnabled(true);
				svMovie.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
					@Override
					public boolean onQueryTextSubmit(String query) {
						getSearchResult(query);
						return true;
					}

					@Override
					public boolean onQueryTextChange(String newText) {
						return false;
					}
				});
		}

		return super.onOptionsItemSelected(item);
	}

	public void getSearchResult(String query) {
		mMovieList = new ArrayList<>();
		MovieApiInterface movieApiInterface = MovieApiClientBuilder.getMovieApiClient().create(MovieApiInterface.class);
		Call<MovieResponse> call = movieApiInterface.getSearchResults(new String(Base64.decode(BuildConfig.API_KEY, Base64.DEFAULT)), PreferenceHelper.getLanguage(), query);
		call.enqueue(new Callback<MovieResponse>() {
			@Override
			public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
				try {
					mMovieList.addAll(response.body().getResults());
					rvSearchMovie.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
					CardViewMovieAdapter cardViewMovieAdapter = new CardViewMovieAdapter(mMovieList, R.layout.item_movie, getApplicationContext());
					cardViewMovieAdapter.setmMovieList(mMovieList);
					rvSearchMovie.setAdapter(cardViewMovieAdapter);
				} catch (NullPointerException e) {
					e.printStackTrace();
				}
			}

			@Override
			public void onFailure(Call<MovieResponse> call, Throwable t) {
				Log.e(TAG, t.toString());
			}
		});
	}
}
