package com.safetyNet.alerts.dto.request.floodAlert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FloodAlertDTO {

	private String stationNum;

	private final List<FloodAlertAddress> addressList = new ArrayList<>();

	public String getStationNum() {
		return stationNum;
	}

	public void setStationNum(String stationNum) {
		this.stationNum = stationNum;
	}

	public List<FloodAlertAddress> getAddressList() {
		return Collections.unmodifiableList(addressList);
	}

	public void setAddressList(List<FloodAlertAddress> addressList) {
		this.addressList.clear();
		this.addressList.addAll(addressList);
	}

	public void addAddress(FloodAlertAddress address) {
		addressList.add(address);
	}

}
