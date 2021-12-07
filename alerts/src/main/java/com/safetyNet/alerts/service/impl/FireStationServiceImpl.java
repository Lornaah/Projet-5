package com.safetyNet.alerts.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.safetyNet.alerts.model.FireStation;
import com.safetyNet.alerts.serializer.FireStationSerializer;
import com.safetyNet.alerts.service.FireStationService;

@Service
public class FireStationServiceImpl implements FireStationService {

	@Override
	public List<FireStation> findAll() {

		List<FireStation> liste = new ArrayList<>();
		FireStationSerializer fireStationSerializer = new FireStationSerializer();
		try {
			fireStationSerializer.parse();
		} catch (IOException e) {
			e.printStackTrace();
		}

		liste.addAll(fireStationSerializer.getList());

		return liste;
	}

}
