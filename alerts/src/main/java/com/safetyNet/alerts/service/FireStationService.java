package com.safetyNet.alerts.service;

import java.util.List;

import com.safetyNet.alerts.model.FireStation;

public interface FireStationService extends CrudService<FireStation> {

	List<String> getAddressByFireStation(String fireStationNum);

}
