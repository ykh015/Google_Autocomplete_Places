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

import com.esri.android.map.MapView;


public class SampleActivity extends Activity {
	
	MapView mMapView;
	SearchView searchView,sv_source,sv_dest;
	ImageView iv_directions;
	SearchManager searchManager;
	
	
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

		iv_directions = (ImageView)findViewById(R.id.imageView1);
		
		

		
		int id = searchView.getContext().getResources().getIdentifier("android:id/search_src_text", null, null);
		final TextView textView = (TextView) searchView.findViewById(id);
		textView.setTextColor(Color.WHITE);

    
		searchView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(SampleActivity.this, SearchLocation.class);
				startActivity(i);

			}
		});
		
		iv_directions.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(SampleActivity.this, GetDirections.class);
				startActivity(i);
			}

		});
		
		
//		hiddenPanel.setOnTouchListener(new OnTouchListener() {
//			
//			@Override
//			public boolean onTouch(View v, MotionEvent event) {
//				// TODO Auto-generated method stub
//				switch(event.getAction()){
//				case MotionEvent.ACTION_DOWN:
//					System.out.println("Drag Down");
//					Animation bottomDown = AnimationUtils.loadAnimation(getApplicationContext(),
//							R.anim.bottom_down);
//					hiddenPanel.setAnimation(bottomDown);
//					hiddenPanel.setVisibility(View.GONE);
//
//				}
//
//				return false;
//			}
//		});
//
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