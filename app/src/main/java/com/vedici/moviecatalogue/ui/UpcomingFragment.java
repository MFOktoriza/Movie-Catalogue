package com.vedici.moviecatalogue.ui;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

/**
 * A simple {@link Fragment} subclass.
 */
public class UpcomingFragment extends Fragment {
	private List<Movie> mMovieList;
	private static final String TAG = UpcomingFragment.class.getSimpleName();

	@Nullable
	@BindView(R.id.rv_upcoming_movie)
	RecyclerView rvUpcomingMovie;

	public UpcomingFragment() {
		// Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_upcoming, container, false);
		ButterKnife.bind(this, view);
		this.getUpcomingMovie();
		return view;
	}

	public void getUpcomingMovie() {
		mMovieList = new ArrayList<>();
		MovieApiInterface movieApiInterface = MovieApiClientBuilder.getMovieApiClient().create(MovieApiInterface.class);
		Call<MovieResponse> call = movieApiInterface.getUpcomingMovies(new String(Base64.decode(BuildConfig.API_KEY, Base64.DEFAULT)), PreferenceHelper.getLanguage());
		call.enqueue(new Callback<MovieResponse>() {
			@Override
			public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
				try {
					mMovieList.addAll(response.body().getResults());
					rvUpcomingMovie.setLayoutManager(new LinearLayoutManager(getContext()));
					CardViewMovieAdapter cardViewMovieAdapter = new CardViewMovieAdapter(mMovieList, R.layout.item_movie, getContext());
					cardViewMovieAdapter.setmMovieList(mMovieList);
					rvUpcomingMovie.setAdapter(cardViewMovieAdapter);
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
