package com.vedici.moviecatalogue;

public class PreferenceHelper {
	private static final String BASE_URL = "https://api.themoviedb.org/";
	private static final String IMAGE_URL = "http://image.tmdb.org/t/p/original/";
	private static final String LANGUAGE = "en-US";

	public static String getBaseUrl() {
		return BASE_URL;
	}

	public static String getImageUrl() {
		return IMAGE_URL;
	}

	public static String getLanguage() {
		return LANGUAGE;
	}
}
