/*
 * Creep Calculator and all related code belongs to and was created by Tyler Caffelle using all original
 * code from the starting date of Tuesday October 15th 2013. All Rights Reserved
 * 
 * DoTA (Defense of the Ancients)2 and all related icons and assets are property of Valve Corporation All Rights 
 * Reserved  All trademarks are property of their respective owners in the US and other countries.
 ************************************************************************************
 */
package com.example.creepcalulator;

import java.io.IOException;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends Activity {

	public Spinner spinner;

	TextView selection, creepsKilled, goldEarned, originalPrice;

	ImageButton itemImage;

	ImageButton Options;	

	MainCalculate mainCalc = new MainCalculate();

	LoadData load = new LoadData();

	public static enum ItemCatagory {
		consum, att, arc, arm, com, sup, cast, wea, armor, art, sec
	};

	public static ItemCatagory ListName = ItemCatagory.consum;

	public static String PACKAGE_NAME;

	 final Context context = this;

	ArrayAdapter<String> dataAdapter;

	ArrayList<String> temp = new ArrayList<String>();

	InventoryItem currentItem;
	
	AlertDialog.Builder builder;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Find all necessary objects
		spinner = (Spinner) findViewById(R.id.itemList);

		PACKAGE_NAME = getApplicationContext().getPackageName();

		creepsKilled = (TextView) findViewById(R.id.CreepsKilled);
		goldEarned = (TextView) findViewById(R.id.GoldEarned);
		originalPrice = (TextView) findViewById(R.id.OriginalPrice);

		itemImage = (ImageButton) findViewById(R.id.clickedButton);
		

		dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_dropdown_item, temp);
		dataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(dataAdapter);
		itemImage.setVisibility(View.GONE);			
		
		builder = new AlertDialog.Builder(this);
		
		builder.setItems(R.array.Item_Lists,
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						
						// TODO Auto-generated method stub
						//spinner.setVisibility(View.GONE);
						
						UpdateList(which);
						
					}
				});
		builder.setTitle("Items Lists");
		builder.create();
	
	}

	void Update() {

		try {
			load.startParser(this, ListName);		
			
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			System.out.println("pull parser problem");		
			DesPopup temp = new DesPopup();
			temp.createPopup("Error", "An Error has accord with the Pull Parser", this);			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("file input problem");	
			DesPopup temp = new DesPopup();
			temp.createPopup("Error", "An Error Has occured with the file Input", this);			
		}catch(Exception e){
			System.out.println("Error");
			DesPopup temp = new DesPopup();
			temp.createPopup("Error", "An Error Has occured in loading the file", this);			
		}
		
		load.CreateSpinnerList(this);
		
		loadSpinner(load.TempInventoryList);
	

		spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {

				String gold;
				String deadCreeps;

				String selected = spinner.getSelectedItem().toString();

				int goldTarget = 0;

				for (int i = 0; i < load.InventoryList.size(); i++) {
					InventoryItem temp = load.InventoryList.get(i);

					if (temp.name.equals(selected)) {						
						currentItem = temp;
						goldTarget = temp.price;
						String price_org = String.valueOf(temp.price);
						originalPrice.setText(price_org);
						try {

							itemImage.setImageDrawable(temp.itemImage);
							itemImage.setVisibility(View.VISIBLE);
						} catch (Exception e) {
							DesPopup desc = new DesPopup();
							desc.createPopup("Error", "Error loading this items image", context);						
						}
						;
					}
				}
				mainCalc.calculateGoldEarned(goldTarget);
				// convert int's to strings
				gold = String.valueOf(mainCalc.GoldRecieved);
				deadCreeps = String.valueOf(mainCalc.CreepsKilled);

				goldEarned.setText(gold);
				creepsKilled.setText(deadCreeps);
			}

			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void OpenOptions(View view) {

		
		builder.show();
		
		/*
		 * Intent newIntent = new Intent(view.getContext(),Options_Menu.class);
		 * startActivity(newIntent);
		 */
	}

	void loadSpinner(ArrayList<String> ItemList) {
		dataAdapter.clear();

		dataAdapter.addAll(ItemList);

		dataAdapter.notifyDataSetChanged();
		spinner.setVisibility(View.VISIBLE);
		spinner.setSelection(0);

	}

	void changeList(int selection) {
		
		switch (selection) {
		case 0:
			ListName = ItemCatagory.consum;			
			break;
		case 1:
			ListName = ItemCatagory.att;			
			break;
		case 2:
			ListName = ItemCatagory.arm;
			break;
		case 3:
			ListName = ItemCatagory.arc;
			break;
		case 4:
			ListName = ItemCatagory.com;
			break;
		case 5:
			ListName = ItemCatagory.sup;
			break;
		case 6:
			ListName = ItemCatagory.cast;
			break;
		case 7:
			ListName = ItemCatagory.wea;
			break;
		case 8:
			ListName = ItemCatagory.armor;
			break;
		case 9:
			ListName = ItemCatagory.art;
			break;
		case 10:
			ListName = ItemCatagory.sec;
			break; 
		}
		Update();
	}

	public void ShowDescPopup(View view) {	
		DesPopup temp = new DesPopup();
		try{
	
			temp.createPopup(currentItem.name, currentItem.use, this);
		}catch(Exception e){
			
			temp.createPopup("Error", "Error Loading description", this);
		}
		
	}
	
	public void UpdateList(int selection)	
	{
		changeList(selection);
	}


}
