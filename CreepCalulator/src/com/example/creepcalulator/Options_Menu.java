package com.example.creepcalulator;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

public class Options_Menu extends Activity{
	
	public Spinner ListSpinner;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_options__menu);
		// Show the Up button in the action bar.
		setupActionBar();
		
		ListSpinner = (Spinner)findViewById(R.id.ListSpinner);
		
		ListSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				
				
				switch(ListSpinner.getSelectedItemPosition()){
				case 0:
					MainActivity.ListName = MainActivity.ItemCatagory.consum;
					break;
				case 1:
					MainActivity.ListName = MainActivity.ItemCatagory.att;					
					break;
				case 2:
					MainActivity.ListName = MainActivity.ItemCatagory.arm;
					break;
				case 3:
					MainActivity.ListName= MainActivity.ItemCatagory.arc;
					break;				
				case 4:
					MainActivity.ListName = MainActivity.ItemCatagory.com;
					break;
				case 5:
					MainActivity.ListName = MainActivity.ItemCatagory.sup;
					break;
				case 6:
					MainActivity.ListName = MainActivity.ItemCatagory.cast;
					break;
				case 7:
					MainActivity.ListName = MainActivity.ItemCatagory.wea;
					break;
				case 8: 
					MainActivity.ListName = MainActivity.ItemCatagory.armor;
					break;
				case 9:
					MainActivity.ListName = MainActivity.ItemCatagory.art;
					break;
				case 10:
					MainActivity.ListName = MainActivity.ItemCatagory.sec;
					break;
				}
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.options__menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void GoBack(View view){
		Intent newIntent = new Intent(view.getContext(),MainActivity.class);
		startActivity(newIntent);
	}
	

}
