package com.example.creepcalulator;

import android.app.Activity;
import android.graphics.drawable.Drawable;



public class InventoryItem extends MainActivity {
	
	public String name,imageName,use;
	
	public int price;	
	
	public Drawable itemImage;
	

	
	public InventoryItem(Activity activity,String name,int price,String imageName,String use){
		this.name = name;
		this.price = price;	
		this.imageName = imageName;
		this.use = use;
		setItemImage(activity,imageName);
	
	}
	
	void setItemImage(Activity activity,String imageName){
		try{
			int id = activity.getResources().getIdentifier(imageName, "drawable", PACKAGE_NAME);
			
			itemImage = activity.getResources().getDrawable(id);
			
			}catch(Exception e){};
		
	}
	
	
	

}
