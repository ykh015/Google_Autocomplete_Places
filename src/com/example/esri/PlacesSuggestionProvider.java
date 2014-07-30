package com.example.esri;

import java.util.ArrayList;


import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

public class PlacesSuggestionProvider extends Activity {
	private static final String LOG_TAG = "PlacesSuggestionProvider";


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		System.out.println("In onCreate PlaceSuggestionsProvider");
		
		Intent intent = getIntent();
		if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
			String query = intent.getStringExtra(SearchManager.QUERY);
			//System.out.println(query);
			new RetrieveFeedTask().execute(query);
		}

	}
	
	
	class RetrieveFeedTask extends AsyncTask<String, Void, ArrayList<String>> {


	    protected ArrayList<String> doInBackground(String... input) {
		    ArrayList<String> resultList = null;
//		     resultList = autocomplete(input[0]);
		    return resultList;
	    }

	    protected void onPostExecute(ArrayList<String> result) {
	    	
	    }
	}

	
	

}
