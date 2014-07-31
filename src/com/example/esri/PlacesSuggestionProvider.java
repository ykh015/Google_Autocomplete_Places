package com.example.esri;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;

public class PlacesSuggestionProvider extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		System.out.println("In onCreate PlaceSuggestionsProvider");

		Intent intent = getIntent();
		System.out.println(intent.getAction());
		if (Intent.ACTION_VIEW.equals(intent.getAction())) {
			String data = intent.getDataString();
			System.out.println(data);
		} else if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
			String query = intent.getStringExtra(SearchManager.QUERY);
			System.out.println(query);
		}

	}

}
