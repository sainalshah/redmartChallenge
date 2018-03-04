package com.sainal.redmartchallenge;

public class PathProperty {
	private int length, startElevation, lowestElevation;

	public PathProperty(int startElevation) {
		
		// startElevation is the elevation of first node
		this.length = 1;
		this.lowestElevation = startElevation;
		this.startElevation = startElevation;
	}

	public PathProperty(int length, int startElevation, int lowestElevation) {
		this.length = length;
		this.lowestElevation = lowestElevation;
		this.startElevation = startElevation;
	}

	public int getLength() {
		return length;
	}

	public void incrementLength() {
		this.length++;
	}

	public int getLowestElevation() {
		return lowestElevation;
	}

	public void setLowestElevation(int elevation) {
		this.lowestElevation = elevation;
	}

	public int getStartElevation() {
		return startElevation;
	}

	public void setStartElevation(int elevation) {
		this.startElevation = elevation;
	}

	public int getDrop() {
		return startElevation - lowestElevation;
	}
	
	public String display() {
		// return the concat of length and drop, which is the required answer
		return "" + length + getDrop();		
	}
}
