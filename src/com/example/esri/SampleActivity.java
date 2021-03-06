package com.example.esri;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.esri.android.map.MapView;


public class SampleActivity extends Activity {
	
	MapView mMapView;
	SearchView searchView,sv_source,sv_dest;
	ImageView iv_directions;
	SearchManager searchManager;
	TextView textView;
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        
		getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
	    getActionBar().hide();

        setContentView(R.layout.main);

        searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

		mMapView = (MapView)findViewById(R.id.map);
		searchView = (SearchView)findViewById(R.id.searchView1);
        searchView.setIconifiedByDefault(false);

		iv_directions = (ImageView)findViewById(R.id.imageView1);
				
		int id = searchView.getContext().getResources().getIdentifier("android:id/search_src_text", null, null);
		textView = (TextView) searchView.findViewById(id);
		textView.setTextColor(Color.WHITE);

    
		searchView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(SampleActivity.this, SearchLocation.class);
				startActivityForResult(i, 1);

			}
		});
		
		iv_directions.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(SampleActivity.this, GetDirections.class);
				startActivity(i);
			}

		});
		
		textView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(SampleActivity.this, SearchLocation.class);
				startActivityForResult(i, 1);

			}
		});
		
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

    	System.out.println("In Activity Result");
        if (requestCode == 1) {
        	System.out.println("Request code is 1");
            if(resultCode == RESULT_OK){
            	System.out.println(RESULT_OK);

                String result=data.getStringExtra("result");
                System.out.println(result);
                searchView.setIconifiedByDefault(false);
                searchView.setQuery(result, false);
                Toast.makeText(this, result, Toast.LENGTH_LONG).show();
                
            }
            if (resultCode == RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }//onActivityResult

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