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



		
		sv_source.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(GetDirections.this, SearchLocation.class);
				startActivity(i);

			}
		});
		sv_dest.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(GetDirections.this, SearchLocation.class);
				startActivity(i);

			}
		});

		slideUp();

	}

	private void slideUp() {
		// TODO Auto-generated method stub
		Animation bottomUp = AnimationUtils.loadAnimation(
				getApplicationContext(), R.anim.bottom_up);

		hiddenPanel.setAnimation(bottomUp);
		hiddenPanel.setVisibility(View.VISIBLE);

	}

}
