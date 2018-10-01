package com.vedici.moviecatalogue.api;

import com.vedici.moviecatalogue.PreferenceHelper;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieApiClientBuilder {
	private static Retrofit mRetrofit = null;

	public static Retrofit getMovieApiClient() {
		if (mRetrofit == null) {
			mRetrofit = new Retrofit.Builder()
					.baseUrl(PreferenceHelper.getBaseUrl())
					.addConverterFactory(GsonConverterFactory.create())
					.build();
		}
		return mRetrofit;
	}
}
