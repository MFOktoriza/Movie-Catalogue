package com.vedici.moviecatalogue.api;

import com.vedici.moviecatalogue.model.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApiInterface {
	@GET("3/movie/now_playing")
	Call<MovieResponse> getNowPlayingMovies(
			@Query("api_key") String apiKey,
			@Query("language") String language
	);

	@GET("3/movie/upcoming")
	Call<MovieResponse> getUpcomingMovies(
			@Query("api_key") String apiKey,
			@Query("language") String language
	);

	@GET("3/search/movie")
	Call<MovieResponse> getSearchResults(
			@Query("api_key") String apiKey,
			@Query("language") String language,
			@Query("query") String query
	);

}
