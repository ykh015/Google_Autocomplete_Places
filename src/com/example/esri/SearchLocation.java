package com.example.esri;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;

public class SearchLocation extends Activity {

	SearchView searchview;
	SearchManager searchManager;
	CursorAdapter adapter;
	ListView lv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.searchlocation);
		searchview = (SearchView) findViewById(R.id.searchLocation);
		lv = (ListView) findViewById(R.id.listView1);

		lv.setAdapter(adapter);
		searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

		searchview.setSearchableInfo(searchManager
				.getSearchableInfo(getComponentName()));
		searchview.setIconifiedByDefault(false);
		
		adapter=searchview.getSuggestionsAdapter();
		searchview.setSuggestionsAdapter(adapter);
		
//		searchview.setSuggestionsAdapter(adapter);
		searchview.setOnQueryTextListener(new OnQueryTextListener() {

			@Override
			public boolean onQueryTextSubmit(String query) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean onQueryTextChange(String newText) {
				// TODO Auto-generated method stub
				System.out.println("In OnquerytextChanged");
				System.out.println(searchview.getSuggestionsAdapter());
				adapter=searchview.getSuggestionsAdapter();
				lv.setAdapter(adapter);
				return false;
			}
		});
	}
}
