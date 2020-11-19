package com.example.coronaupdate.model.indo;

import com.google.gson.annotations.SerializedName;

public class AccResponse{

	@SerializedName("jumlahKasus")
	private int jumlahKasus;

	@SerializedName("meninggal")
	private int meninggal;

	@SerializedName("sembuh")
	private int sembuh;

	@SerializedName("lastUpdate")
	private long lastUpdate;

	@SerializedName("perawatan")
	private int perawatan;

	public void setJumlahKasus(int jumlahKasus){
		this.jumlahKasus = jumlahKasus;
	}

	public int getJumlahKasus(){
		return jumlahKasus;
	}

	public void setMeninggal(int meninggal){
		this.meninggal = meninggal;
	}

	public int getMeninggal(){
		return meninggal;
	}

	public void setSembuh(int sembuh){
		this.sembuh = sembuh;
	}

	public int getSembuh(){
		return sembuh;
	}

	public void setLastUpdate(long lastUpdate){
		this.lastUpdate = lastUpdate;
	}

	public long getLastUpdate(){
		return lastUpdate;
	}

	public void setPerawatan(int perawatan){
		this.perawatan = perawatan;
	}

	public int getPerawatan(){
		return perawatan;
	}

	@Override
 	public String toString(){
		return 
			"AccResponse{" + 
			"jumlahKasus = '" + jumlahKasus + '\'' + 
			",meninggal = '" + meninggal + '\'' + 
			",sembuh = '" + sembuh + '\'' + 
			",lastUpdate = '" + lastUpdate + '\'' + 
			",perawatan = '" + perawatan + '\'' + 
			"}";
		}
}