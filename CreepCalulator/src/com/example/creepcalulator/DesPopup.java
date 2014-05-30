package com.example.creepcalulator;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.graphics.drawable.Drawable;


public class DesPopup extends MainActivity {
	

	public  void createPopup(String title,String des ,Context context){
		Builder descPopup;
		 descPopup = new AlertDialog.Builder(context);
		 System.out.println(des);
		descPopup.setTitle(title);
		descPopup.setMessage(des);
		descPopup.setPositiveButton("OK", new OnClickListener(){

			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				// TODO Auto-generated method stub
				
			}
			
		});
		descPopup.create();
		descPopup.show();
		
	}	
	
	public  void createPopup(String title,String des ,Context context,Drawable image){
		Builder descPopup;
		 descPopup = new AlertDialog.Builder(context);
		 System.out.println(des);
		descPopup.setTitle(title);
		descPopup.setMessage(des);
		descPopup.setIcon(image);
		descPopup.create();
		descPopup.show();
		
	}	
	
	public  void createPopup(Context context,int itemArray){
		
		
		
	}

}
