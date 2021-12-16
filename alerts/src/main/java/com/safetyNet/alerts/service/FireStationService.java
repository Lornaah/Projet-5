package com.safetyNet.alerts.service;

import java.util.List;

import com.safetyNet.alerts.dto.request.FireAlertDTO;
import com.safetyNet.alerts.dto.request.floodAlert.FloodAlertDTO;
import com.safetyNet.alerts.model.FireStation;

public interface FireStationService extends CrudService<FireStation> {

	List<String> getAddressByFireStation(String fireStationNum);

	List<FloodAlertDTO> getAddressList(List<String> stations);

	FireAlertDTO getStationByAddress(String address);

}
