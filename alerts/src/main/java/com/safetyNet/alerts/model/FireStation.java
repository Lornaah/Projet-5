package com.safetyNet.alerts.model;

import java.util.Objects;

public class FireStation {

	private String address;

	private String station;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getStation() {
		return station;
	}

	public void setStation(String station) {
		this.station = station;
	}

	@Override
	public int hashCode() {
		return Objects.hash(address);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FireStation other = (FireStation) obj;
		return Objects.equals(address, other.address);
	}

}
