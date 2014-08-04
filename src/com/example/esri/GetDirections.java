package com.example.esri;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

public class GetDirections extends Activity {
	SearchView sv_source, sv_dest;
	SearchManager searchManager;
	ViewGroup hiddenPanel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.directions);

		searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

		hiddenPanel = (ViewGroup) findViewById(R.id.hidden_panel);

		sv_source = (SearchView) findViewById(R.id.searchView1);
		sv_dest = (SearchView) findViewById(R.id.searchView2);

		TextView tv_source;
		int id_source = sv_source.getContext().getResources()
				.getIdentifier("android:id/search_src_text", null, null);
		tv_source = (TextView) sv_source.findViewById(id_source);

		TextView tv_dest;
		int id_dest = sv_dest.getContext().getResources()
				.getIdentifier("android:id/search_src_text", null, null);
		tv_dest = (TextView) sv_dest.findViewById(id_dest);

		//onClick listener for text box inside the searchview
		tv_source.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(GetDirections.this, SearchLocation.class);
				// 2 is for source
				startActivityForResult(i, 2);

			}
		});

		tv_dest.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(GetDirections.this, SearchLocation.class);
				// 3 is for destination
				startActivityForResult(i, 3);

			}
		});
		
		sv_source.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(GetDirections.this, SearchLocation.class);
				// 2 is for source
				startActivityForResult(i, 2);

			}
		});
		sv_dest.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(GetDirections.this, SearchLocation.class);
				// 3 is for destination
				startActivityForResult(i, 3);

			}
		});

		slideUp();

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		if (requestCode == 2) {
			if (resultCode == RESULT_OK) {
				sv_source.setIconifiedByDefault(false);
				String result = data.getStringExtra("result");
				Toast.makeText(this, "Source " + result, Toast.LENGTH_LONG)
						.show();
				sv_source.setQuery(result, false);
			}
			if (resultCode == RESULT_CANCELED) {
				// Write your code if there's no result
			}
		} else if (requestCode == 3) {
			if (resultCode == RESULT_OK) {
				sv_dest.setIconifiedByDefault(false);
				String result = data.getStringExtra("result");
				Toast.makeText(this, "Destination " + result, Toast.LENGTH_LONG)
						.show();
				sv_dest.setQuery(result, false);
			}
			if (resultCode == RESULT_CANCELED) {
				// Write your code if there's no result
			}
		}

	}

	private void slideUp() {
		Animation bottomUp = AnimationUtils.loadAnimation(
				getApplicationContext(), R.anim.bottom_up);

		hiddenPanel.setAnimation(bottomUp);
		hiddenPanel.setVisibility(View.VISIBLE);
	}

}
