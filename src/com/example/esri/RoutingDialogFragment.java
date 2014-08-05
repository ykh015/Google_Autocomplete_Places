package com.example.esri;

import java.util.List;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.esri.core.geometry.GeometryEngine;
import com.esri.core.geometry.Point;
import com.esri.core.geometry.SpatialReference;
import com.esri.core.tasks.geocode.Locator;
import com.esri.core.tasks.geocode.LocatorFindParameters;
import com.esri.core.tasks.geocode.LocatorGeocodeResult;

/*
 * This fragment displays a dialog box which contains text fields for source and destination addresses.
 * It also contains two icons "My Location" and "Swap Addresses". When user clciks on My Location icon, the focused
 * text field displays the text "My Location". When "Swap Addresses" icon is clicked the addresses in the text boxes are swapped.
 * When the user clicks on the "Route" button, the addresses are geocoded to points which are then used by the RoutingSample activity to
 * display the route.
 * 
 */

public class RoutingDialogFragment extends DialogFragment implements
		OnClickListener {

	EditText et_source;
	EditText et_destination;
	Locator locator;
	static ProgressDialog dialog;
	static Handler handler;
	Button bGetRoute;

	// For storing the result of Geocoding Task
	List<LocatorGeocodeResult> result_origin = null;
	List<LocatorGeocodeResult> result_destination = null;

	// Interface to be implemented by the activity
	// onGetRoute mCallback;

	// To check if the edit text contains "My Location"
	boolean src_isMyLocation = false;
	boolean dest_isMyLocation = false;

	final SpatialReference wm = SpatialReference.create(102100);
	final SpatialReference egs = SpatialReference.create(4326);
	String source;
	String destination;
	// Image views for the icons
	ImageView img_sCancel, img_dCancel, img_myLocaion, img_swap;

	// Runnable to dismiss the process dialog
	static public class MyRunnable implements Runnable {
		public void run() {
			dialog.dismiss();
		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		handler = new Handler();
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.dialog_layout, container);

		// Set the views from the XML layout
		et_source = (EditText) view.findViewById(R.id.et_source);
		et_destination = (EditText) view.findViewById(R.id.et_destination);
		img_sCancel = (ImageView) view.findViewById(R.id.iv_cancelSource);
		img_dCancel = (ImageView) view.findViewById(R.id.iv_cancelDestination);
		img_swap = (ImageView) view.findViewById(R.id.iv_interchange);
		img_myLocaion = (ImageView) view.findViewById(R.id.iv_myDialogLocation);
		bGetRoute = (Button) view.findViewById(R.id.bGetRoute);

		// Setting onClick listener for the icons on the dialog
		img_dCancel.setOnClickListener(this);
		img_sCancel.setOnClickListener(this);
		img_swap.setOnClickListener(this);
		img_myLocaion.setOnClickListener(this);

		
		//Request Codeis 2 for source and 3 for destination.
		et_source.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(getActivity(), SearchLocation.class);
				if(et_source.getText()!=null)
					i.putExtra("Place", et_source.getText().toString());
				startActivityForResult(i, 2);

			}
		});

		et_destination.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(getActivity(), SearchLocation.class);
				if(et_destination.getText()!=null)
					i.putExtra("Place", et_destination.getText().toString());

				startActivityForResult(i, 3);

			}
		});
		
		// Adding onClick listener for the button
		bGetRoute.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				source = et_source.getText().toString();
				destination = et_destination.getText().toString();

				// If either of the edit text is empty, display the toast
				if (source.equals("") || destination.equals("")) {
					Toast.makeText(getActivity(), "Place cannot be empty",
							Toast.LENGTH_LONG).show();
					return;
				}

				// Checking if the edit text views contain "My Location"
				if (source.equals("My Location"))
					src_isMyLocation = true;
				if (destination.equals("My Location"))
					dest_isMyLocation = true;

				// If source and destination are not same then geocode the
				// addresses
				if (!source.equals(destination)) {
					// Geocode the addresses
					// geocode(source, destination);
				} else {
					Toast.makeText(getActivity(),
							"Source and Destination should be different",
							Toast.LENGTH_LONG).show();
				}

			}
		});

		// Removing title from the dialog box
		getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

		return view;
	}

	@Override
	public void onStop() {
		super.onStop();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();

	}

	// OnClick events for the icons on the dialog box
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.iv_myDialogLocation:

			// Putting "My Location" in the edit text that is in focus
			if (et_source.hasFocus())
				et_source.setText("My Location");
			else
				et_destination.setText("My Location");
			break;
		case R.id.iv_interchange:

			// Swapping the values
			String temp = et_source.getText().toString();
			et_source.setText(et_destination.getText().toString());
			et_destination.setText(temp);
			break;

		}

	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		System.out.println("In Fragment onActivityResult");
		if (requestCode == 2) {
			if (resultCode == Activity.RESULT_OK) {
				String result = data.getStringExtra("result");
				et_source.setText(result);
				et_source.setSelection(et_source.getText().length());
			}
			if (resultCode == Activity.RESULT_CANCELED) {
				// Write your code if there's no result
			}
		} else if (requestCode == 3) {
			if (resultCode == Activity.RESULT_OK) {
				String result = data.getStringExtra("result");
				et_destination.setText(result);
				et_destination.setSelection(et_destination.getText().length());

			}
			if (resultCode == Activity.RESULT_CANCELED) {
				// Write your code if there's no result
			}
		}

	}// onActivityResult

}
