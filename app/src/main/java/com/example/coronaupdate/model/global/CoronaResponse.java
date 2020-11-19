package com.example.coronaupdate.model.global;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class CoronaResponse{

	@SerializedName("Message")
	private String message;

	@SerializedName("Countries")
	private List<CountriesItem> countries;

	@SerializedName("Global")
	private Global global;

	@SerializedName("Date")
	private String date;

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setCountries(List<CountriesItem> countries){
		this.countries = countries;
	}

	public List<CountriesItem> getCountries(){
		return countries;
	}

	public void setGlobal(Global global){
		this.global = global;
	}

	public Global getGlobal(){
		return global;
	}

	public void setDate(String date){
		this.date = date;
	}

	public String getDate(){
		return date;
	}

	@Override
 	public String toString(){
		return 
			"CoronaResponse{" + 
			"message = '" + message + '\'' + 
			",countries = '" + countries + '\'' + 
			",global = '" + global + '\'' + 
			",date = '" + date + '\'' + 
			"}";
		}
}