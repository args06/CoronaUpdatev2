package com.example.coronaupdate.model.global;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class CountriesItem implements Parcelable {

	@SerializedName("NewRecovered")
	private int newRecovered;

	@SerializedName("NewDeaths")
	private int newDeaths;

	@SerializedName("TotalRecovered")
	private int totalRecovered;

	@SerializedName("TotalConfirmed")
	private int totalConfirmed;

	@SerializedName("Country")
	private String country;

	@SerializedName("Premium")
	private Premium premium;

	@SerializedName("CountryCode")
	private String countryCode;

	@SerializedName("Slug")
	private String slug;

	@SerializedName("NewConfirmed")
	private int newConfirmed;

	@SerializedName("TotalDeaths")
	private int totalDeaths;

	@SerializedName("Date")
	private String date;

	protected CountriesItem(Parcel in) {
		newRecovered = in.readInt();
		newDeaths = in.readInt();
		totalRecovered = in.readInt();
		totalConfirmed = in.readInt();
		country = in.readString();
		countryCode = in.readString();
		slug = in.readString();
		newConfirmed = in.readInt();
		totalDeaths = in.readInt();
		date = in.readString();
	}

	public static final Creator<CountriesItem> CREATOR = new Creator<CountriesItem>() {
		@Override
		public CountriesItem createFromParcel(Parcel in) {
			return new CountriesItem(in);
		}

		@Override
		public CountriesItem[] newArray(int size) {
			return new CountriesItem[size];
		}
	};

	public void setNewRecovered(int newRecovered){
		this.newRecovered = newRecovered;
	}

	public int getNewRecovered(){
		return newRecovered;
	}

	public void setNewDeaths(int newDeaths){
		this.newDeaths = newDeaths;
	}

	public int getNewDeaths(){
		return newDeaths;
	}

	public void setTotalRecovered(int totalRecovered){
		this.totalRecovered = totalRecovered;
	}

	public int getTotalRecovered(){
		return totalRecovered;
	}

	public void setTotalConfirmed(int totalConfirmed){
		this.totalConfirmed = totalConfirmed;
	}

	public int getTotalConfirmed(){
		return totalConfirmed;
	}

	public void setCountry(String country){
		this.country = country;
	}

	public String getCountry(){
		return country;
	}

	public void setPremium(Premium premium){
		this.premium = premium;
	}

	public Premium getPremium(){
		return premium;
	}

	public void setCountryCode(String countryCode){
		this.countryCode = countryCode;
	}

	public String getCountryCode(){
		return countryCode;
	}

	public void setSlug(String slug){
		this.slug = slug;
	}

	public String getSlug(){
		return slug;
	}

	public void setNewConfirmed(int newConfirmed){
		this.newConfirmed = newConfirmed;
	}

	public int getNewConfirmed(){
		return newConfirmed;
	}

	public void setTotalDeaths(int totalDeaths){
		this.totalDeaths = totalDeaths;
	}

	public int getTotalDeaths(){
		return totalDeaths;
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
			"CountriesItem{" + 
			"newRecovered = '" + newRecovered + '\'' + 
			",newDeaths = '" + newDeaths + '\'' + 
			",totalRecovered = '" + totalRecovered + '\'' + 
			",totalConfirmed = '" + totalConfirmed + '\'' + 
			",country = '" + country + '\'' + 
			",premium = '" + premium + '\'' + 
			",countryCode = '" + countryCode + '\'' + 
			",slug = '" + slug + '\'' + 
			",newConfirmed = '" + newConfirmed + '\'' + 
			",totalDeaths = '" + totalDeaths + '\'' + 
			",date = '" + date + '\'' + 
			"}";
		}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(newRecovered);
		dest.writeInt(newDeaths);
		dest.writeInt(totalRecovered);
		dest.writeInt(totalConfirmed);
		dest.writeString(country);
		dest.writeString(countryCode);
		dest.writeString(slug);
		dest.writeInt(newConfirmed);
		dest.writeInt(totalDeaths);
		dest.writeString(date);
	}
}