package com.example.creepcalulator;

import java.io.IOException;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;

import com.example.creepcalulator.MainActivity.ItemCatagory;


public class LoadData{
	//TODO: Use this class to pair up the gold and item arrays
	//public List<String> ItemList = new ArrayList<String>();
	
	public ArrayList<InventoryItem> InventoryList = new ArrayList<InventoryItem>(); //List that holds the objects
	
	public ArrayList<String> TempInventoryList = new ArrayList<String>(); //List that holds the strings for the spinner
	
	
	

public void startParser(Activity activity, ItemCatagory catagory) throws XmlPullParserException, IOException,Exception{
		
	Resources res = activity.getResources();

	 XmlResourceParser xpp = res.getXml(R.xml.items_prices);
	 
	 xpp.next();
	 
	 InventoryList.clear();
	 
	 int eventType = xpp.getEventType();
	 int price = 0;
	 String temp = String.valueOf(catagory);
	String item,imageName,use = null;
	 while(eventType != XmlPullParser.END_DOCUMENT){
		 if(eventType == XmlPullParser.START_TAG){		 
				 if(xpp.getName().equals("item")){
					 if(xpp.getAttributeValue(null, "type").equals(temp)){
						 price = Integer.parseInt( xpp.getAttributeValue(null, "price"));	
						 item = xpp.getAttributeValue(null, "name");
						 imageName = xpp.getAttributeValue(null, "imageName");
						 use = xpp.getAttributeValue(null, "use");						 
						 InventoryList.add(new InventoryItem(activity,item,price,imageName,use));
						 use = null;
						 item = null;
						 imageName = null;
						 price = 0;
						
						
					 }
				 }		 
				 
			 }
		
		 
		 
		 eventType = xpp.next();

	 } 
	
}

public ArrayList<String> CreateSpinnerList(Context context){	
	TempInventoryList.clear();
	for(int i=0; i<InventoryList.size();i++){
		InventoryItem tempItem = InventoryList.get(i);		
		TempInventoryList.add(tempItem.name);	
		}
	return TempInventoryList;
	
	}
}
	


