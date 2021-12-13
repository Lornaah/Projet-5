package com.safetyNet.alerts.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.safetyNet.alerts.model.FireStation;
import com.safetyNet.alerts.repository.FireStationRepository;
import com.safetyNet.alerts.service.FireStationService;

@Service
public class FireStationServiceImpl implements FireStationService {
	@Autowired
	@Qualifier("fireStationRepoSingleton")
	FireStationRepository fireStationRepository;

	@Override
	public void add(FireStation element) {
		fireStationRepository.add(element);
	}

	@Override
	public void delete(FireStation element) {
		fireStationRepository.delete(element);
	}

	@Override
	public void update(FireStation element) {
		fireStationRepository.update(element);
	}

	@Override
	public List<FireStation> findAll() {
		return fireStationRepository.findAll();
	}

	@Override
	public void addAll(List<FireStation> elements) {
		fireStationRepository.addAll(elements);
	}

}
