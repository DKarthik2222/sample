package com.hotelManagement.angular.hotelmanagement.booking;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import org.springframework.stereotype.Component;

import net.minidev.json.JSONObject;

@Component
public class BookingUtils {
	   public File writeToCsv(JSONObject obj){
	 	  File file=new File(obj.get("id")+".csv");
		      try {	    	  
		         BufferedWriter fileWriter = new BufferedWriter(new FileWriter(file));     
		         fileWriter.write("BOOKING DETAILS:");
	          fileWriter.newLine();
		         fileWriter.write(obj.keySet().toString().substring(1,obj.keySet().toString().length()-1));
	          fileWriter.newLine();
	          fileWriter.write(obj.values().toString().substring(1, obj.values().toString().length()-1)); 
	          fileWriter.close();
		      }
		      catch(Exception e) {
		         e.printStackTrace();
		      }   
		      return file;
	}
	   
}