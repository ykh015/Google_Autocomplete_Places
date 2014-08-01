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
import android.widget.Toast;

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
				//2 is for source
				i.putExtra("From", "source");
				startActivityForResult(i, 2);

			}
		});
		sv_dest.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(GetDirections.this, SearchLocation.class);
				//3 is for destination
				i.putExtra("From", "destination");

				startActivityForResult(i, 3);

			}
		});

		slideUp();

	}
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

    	System.out.println("In Activity Result");
        if (requestCode == 2) {
        	System.out.println("Request code is 1");
            if(resultCode == RESULT_OK){
            	System.out.println(RESULT_OK);

                String result=data.getStringExtra("result");
                System.out.println(result);
                Toast.makeText(this, "Source "+result, Toast.LENGTH_LONG).show();
                
            }
            if (resultCode == RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
        else if(requestCode == 3){
            if(resultCode == RESULT_OK){
            	System.out.println(RESULT_OK);

                String result=data.getStringExtra("result");
                System.out.println(result);
                Toast.makeText(this, "Destination "+result, Toast.LENGTH_LONG).show();
                
            }
            if (resultCode == RESULT_CANCELED) {
                //Write your code if there's no result
            }

        }
    }

	private void slideUp() {
		// TODO Auto-generated method stub
		Animation bottomUp = AnimationUtils.loadAnimation(
				getApplicationContext(), R.anim.bottom_up);

		hiddenPanel.setAnimation(bottomUp);
		hiddenPanel.setVisibility(View.VISIBLE);

	}

}
