package com.example.coronaupdate.model.indo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class DataItem implements Parcelable {

	@SerializedName("fid")
	private int fid;

	@SerializedName("provinsi")
	private String provinsi;

	@SerializedName("kasusMeni")
	private int kasusMeni;

	@SerializedName("kodeProvi")
	private int kodeProvi;

	@SerializedName("kasusSemb")
	private int kasusSemb;

	@SerializedName("kasusPosi")
	private int kasusPosi;

	protected DataItem(Parcel in) {
		fid = in.readInt();
		provinsi = in.readString();
		kasusMeni = in.readInt();
		kodeProvi = in.readInt();
		kasusSemb = in.readInt();
		kasusPosi = in.readInt();
	}

	public static final Creator<DataItem> CREATOR = new Creator<DataItem>() {
		@Override
		public DataItem createFromParcel(Parcel in) {
			return new DataItem(in);
		}

		@Override
		public DataItem[] newArray(int size) {
			return new DataItem[size];
		}
	};

	public int getFid(){
		return fid;
	}

	public String getProvinsi(){
		return provinsi;
	}

	public int getKasusMeni(){
		return kasusMeni;
	}

	public int getKodeProvi(){
		return kodeProvi;
	}

	public int getKasusSemb(){
		return kasusSemb;
	}

	public int getKasusPosi(){
		return kasusPosi;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(fid);
		dest.writeString(provinsi);
		dest.writeInt(kasusMeni);
		dest.writeInt(kodeProvi);
		dest.writeInt(kasusSemb);
		dest.writeInt(kasusPosi);
	}
}