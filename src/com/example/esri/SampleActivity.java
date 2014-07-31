package com.example.esri;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnDragListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import com.esri.android.map.MapView;


public class SampleActivity extends Activity {
	
	MapView mMapView;
	SearchView searchView,sv_source,sv_dest;
	ImageView iv_directions;
	ViewGroup hiddenPanel;
	SearchManager searchManager;
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

         searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        
		mMapView = (MapView)findViewById(R.id.map);
		searchView = (SearchView)findViewById(R.id.searchView1);
		sv_source= (SearchView)findViewById(R.id.searchView2);
		sv_dest= (SearchView)findViewById(R.id.searchView3);

		iv_directions = (ImageView)findViewById(R.id.imageView1);
		hiddenPanel=(ViewGroup)findViewById(R.id.hidden_panel);
		
		searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
		searchView.setIconifiedByDefault(false); 
		
		sv_source.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
		sv_source.setIconifiedByDefault(false); 

		sv_dest.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
		sv_dest.setIconifiedByDefault(false); 

		
		int id = searchView.getContext().getResources().getIdentifier("android:id/search_src_text", null, null);
		final TextView textView = (TextView) searchView.findViewById(id);
		textView.setTextColor(Color.WHITE);

    
		iv_directions.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				slideUp();
				InputMethodManager imm = (InputMethodManager)getSystemService(
					      Context.INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(textView.getApplicationWindowToken(), 0);

			}

		});
		
		
		hiddenPanel.setOnDragListener(new OnDragListener() {
			
			@Override
			public boolean onDrag(View v, DragEvent event) {
				// TODO Auto-generated method stub
				switch(event.getAction()){
				case DragEvent.ACTION_DRAG_ENDED:
					System.out.println("Drag Down");
					Animation bottomDown = AnimationUtils.loadAnimation(getApplicationContext(),
							R.anim.bottom_down);
					hiddenPanel.setAnimation(bottomDown);
					hiddenPanel.setVisibility(View.GONE);

				}
				return false;
			}
		});

    }

    
	private void slideUp() {
		// TODO Auto-generated method stub
		Animation bottomUp = AnimationUtils.loadAnimation(getApplicationContext(),
				R.anim.bottom_up);

		hiddenPanel.setAnimation(bottomUp);
		hiddenPanel.setVisibility(View.VISIBLE);

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