package com.example.esri;


import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;

public class SearchLocation extends Activity {

	SearchView searchview;
	SearchManager searchManager;
	ArrayAdapter<String> adapter;
	ListView lv;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
		getActionBar().hide();
		
		
		setContentView(R.layout.searchlocation);
		
		searchview = (SearchView) findViewById(R.id.searchLocation);

		handleIntent(getIntent());

		lv = (ListView) findViewById(R.id.listView1);

		searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

		searchview.setSearchableInfo(searchManager
				.getSearchableInfo(getComponentName()));
		searchview.setIconifiedByDefault(false);

		searchview.setOnQueryTextListener(new OnQueryTextListener() {

			@Override
			public boolean onQueryTextSubmit(String query) {

				Intent returnIntent = new Intent();
				returnIntent.putExtra("result", query);
				setResult(RESULT_OK, returnIntent);
				finish();
				return true;
			}

			@Override
			public boolean onQueryTextChange(String newText) {

				return true;
			}
		});

		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				String data = (String) parent.getItemAtPosition(position);
				Intent returnIntent = new Intent();
				returnIntent.putExtra("result", data);
				setResult(RESULT_OK, returnIntent);
				finish();

			}
		});
		
	
	}
	
	public void handleIntent(Intent i){
		if(i.getStringExtra("Place")!=null){
			String value = i.getStringExtra("Place");
			searchview.setQuery(value, false);
		}
	}




}
