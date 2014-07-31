package com.example.esri;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.widget.SearchView;

public class SearchLocation extends Activity {

	SearchView searchview;
	SearchManager searchManager;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.searchlocation);
		searchview = (SearchView)findViewById(R.id.searchLocation);
       
		searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

		searchview.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
		searchview.setIconifiedByDefault(false); 

		
		
	}
}
