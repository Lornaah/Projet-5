package com.safetyNet.alerts.repository;

import java.util.List;

import com.safetyNet.alerts.model.FireStation;

public interface FireStationRepository extends CrudRepository<FireStation> {

	List<String> getAddressByFireStation(String fireStationNum);

}
