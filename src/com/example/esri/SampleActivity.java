package com.example.esri;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.SearchView;
import android.widget.TextView;

import com.esri.android.map.MapView;


public class SampleActivity extends Activity {
	
	MapView mMapView;
	SearchView searchView;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        
		mMapView = (MapView)findViewById(R.id.map);
		searchView = (SearchView)findViewById(R.id.searchView1);
		searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
		searchView.setIconifiedByDefault(false); 
		
		
		int id = searchView.getContext().getResources().getIdentifier("android:id/search_src_text", null, null);
		TextView textView = (TextView) searchView.findViewById(id);
		textView.setTextColor(Color.WHITE);

    }

	@Override
	protected void onDestroy() {
		super.onDestroy();
 }
	@Override
	protected void onPause() {
		super.onPause();
		mMapView.pause();
 }
	@Override
	protected void onResume() {
		super.onResume();
		mMapView.unpause();
	}

}