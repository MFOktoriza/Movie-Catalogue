package com.vedici.moviecatalogue.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.vedici.moviecatalogue.CustomOnItemClickListener;
import com.vedici.moviecatalogue.PreferenceHelper;
import com.vedici.moviecatalogue.R;
import com.vedici.moviecatalogue.model.Movie;
import com.vedici.moviecatalogue.ui.MainActivity;
import com.vedici.moviecatalogue.ui.MovieDetailActivity;
import com.vedici.moviecatalogue.ui.MoviePosterActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CardViewMovieAdapter extends RecyclerView.Adapter<CardViewMovieAdapter.MovieViewAdapter> {
	private Context mContext;
	private List<Movie> mMovieList;
	private CustomOnItemClickListener.OnItemClickCallback mOnItemClickCallback;

	public CardViewMovieAdapter(List<Movie> movieList, int cardViewLayout, Context context) {
		this.mMovieList = movieList;
		int mCardViewLayout = cardViewLayout;
		this.mContext = context;
	}

	public List<Movie> getmMovieList() {
		return mMovieList;
	}

	public void setmMovieList(List<Movie> mMovieList) {
		this.mMovieList = mMovieList;
	}

	@NonNull
	@Override
	public MovieViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
		return new MovieViewAdapter(view);
	}

	@Override
	public void onBindViewHolder(@NonNull MovieViewAdapter holder, int position) {
		final Movie movie = getmMovieList().get(position);
		Glide.with(mContext)
				.load(PreferenceHelper.getImageUrl() + movie.getPoster_path())
				.apply(new RequestOptions()
						.placeholder(R.drawable.ic_landscape_black_24dp)
						.override(150, 200)
						.fitCenter())
				.into(holder.imgMoviePoster);
		holder.tvMovieTitle.setText(movie.getOriginal_title());
		holder.tvMovieOverview.setText(movie.getOverview());
		holder.tvMovieReleaseDate.setText(movie.getRelease_date());
		holder.btnViewDetail.setOnClickListener(new CustomOnItemClickListener(position, new CustomOnItemClickListener.OnItemClickCallback() {
			@Override
			public void onItemClicked(View view, int position) {
				Intent movieViewPosterIntent = new Intent(mContext, MovieDetailActivity.class);
				movieViewPosterIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				movieViewPosterIntent.putExtra(MovieDetailActivity.EXTRA_MOVIE, movie);
				view.getContext().startActivity(movieViewPosterIntent);
			}
		}));
		holder.btnViewPoster.setOnClickListener(new CustomOnItemClickListener(position, new CustomOnItemClickListener.OnItemClickCallback() {
			@Override
			public void onItemClicked(View view, int position) {
				Intent movieViewPosterIntent = new Intent(mContext, MoviePosterActivity.class);
				movieViewPosterIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				movieViewPosterIntent.putExtra(MovieDetailActivity.EXTRA_MOVIE, movie);
				view.getContext().startActivity(movieViewPosterIntent);
			}
		}));
	}

	@Override
	public int getItemCount() {
		return getmMovieList().size();
	}

	public class MovieViewAdapter extends RecyclerView.ViewHolder {
		@BindView(R.id.img_movie_poster)
		ImageView imgMoviePoster;
		@BindView(R.id.tv_movie_title)
		TextView tvMovieTitle;
		@BindView(R.id.tv_movie_overview)
		TextView tvMovieOverview;
		@BindView(R.id.tv_movie_release_date)
		TextView tvMovieReleaseDate;
		@BindView(R.id.btn_view_detail)
		Button btnViewDetail;
		@BindView(R.id.btn_view_poster)
		Button btnViewPoster;

		public MovieViewAdapter(View itemView) {
			super(itemView);
			ButterKnife.bind(this, itemView);
		}
	}
}
