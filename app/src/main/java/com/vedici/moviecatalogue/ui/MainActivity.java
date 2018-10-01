package com.vedici.moviecatalogue.ui;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;

import com.vedici.moviecatalogue.R;
import com.vedici.moviecatalogue.adapter.ViewPagerAdapter;
import com.vedici.moviecatalogue.api.MovieApiClientBuilder;
import com.vedici.moviecatalogue.api.MovieApiInterface;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
	private static final String TAG = MainActivity.class.getSimpleName();

	@Nullable
	@BindView(R.id.page_tab_layout)
	TabLayout mPageTabLayout;
	@BindView(R.id.fragment_container)
	ViewPager mFragmentContainer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButterKnife.bind(this);
		getSupportActionBar().setDisplayShowTitleEnabled(true);
		getSupportActionBar().setElevation(0);

		mPageTabLayout.addTab(mPageTabLayout.newTab().setText(R.string.now_playing));
		mPageTabLayout.addTab(mPageTabLayout.newTab().setText(R.string.upcoming));
		mPageTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

		getSelectedTab();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_option_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.change_language_menu:
				Intent changeLanguageIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
				startActivity(changeLanguageIntent);
				break;
			case R.id.search_button:
				Intent searchMovieIntent = new Intent(MainActivity.this, SearchMovieActivity.class);
				startActivity(searchMovieIntent);
				break;
		}

		return super.onOptionsItemSelected(item);
	}

	public void getSelectedTab() {
		ViewPagerAdapter pageAdapter = new ViewPagerAdapter(getSupportFragmentManager(), mPageTabLayout.getTabCount());
		mFragmentContainer.setAdapter(pageAdapter);
		mFragmentContainer.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mPageTabLayout));
		mPageTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
			@Override
			public void onTabSelected(TabLayout.Tab tab) {
				mFragmentContainer.setCurrentItem(tab.getPosition());
			}

			@Override
			public void onTabUnselected(TabLayout.Tab tab) {

			}

			@Override
			public void onTabReselected(TabLayout.Tab tab) {

			}
		});
	}
}
