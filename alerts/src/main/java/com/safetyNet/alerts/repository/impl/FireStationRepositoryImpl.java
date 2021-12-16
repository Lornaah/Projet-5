package com.safetyNet.alerts.repository.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.safetyNet.alerts.dto.request.FireAlertDTO;
import com.safetyNet.alerts.dto.request.floodAlert.FloodAlertAddress;
import com.safetyNet.alerts.dto.request.floodAlert.FloodAlertDTO;
import com.safetyNet.alerts.model.FireStation;
import com.safetyNet.alerts.repository.FireStationRepository;

@Repository
public class FireStationRepositoryImpl implements FireStationRepository {

	private final List<FireStation> list = new ArrayList<>();

	@Bean
	@Scope("singleton")
	public FireStationRepositoryImpl fireStationRepoSingleton() {
		return new FireStationRepositoryImpl();
	}

	@Override
	public void add(FireStation element) {
		list.add(element);
	}

	@Override
	public void delete(FireStation element) {
		list.remove(element);
	}

	@Override
	public void update(FireStation element) {
		list.forEach(f -> {
			if (f.equals(element)) {
				f.setStation(element.getStation());
			}
		});
	}

	@Override
	public List<FireStation> findAll() {
		return Collections.unmodifiableList(list);
	}

	@Override
	public void addAll(List<FireStation> elements) {
		list.addAll(elements);
	}

	@Override
	public void clear() {
		list.clear();
	}

	@Override
	public List<String> getAddressByFireStation(String fireStationNum) {
		List<String> address = new ArrayList<>();
		list.forEach(f -> {
			if (f.getStation().equals(fireStationNum)) {
				address.add(f.getAddress());
			}
		});
		return address;
	}

	@Override
	public List<FloodAlertDTO> getAddressList(List<String> stations) {
		List<FloodAlertDTO> addressList = new ArrayList<>();
		stations.forEach(s -> {
			FloodAlertDTO floodAlertDTO = new FloodAlertDTO();
			floodAlertDTO.setStationNum(s);

			List<String> addresses = getAddressByFireStation(s);
			addresses.forEach(a -> {
				FloodAlertAddress floodAlertAddress = new FloodAlertAddress();
				floodAlertAddress.setAddress(a);
				floodAlertDTO.addAddress(floodAlertAddress);
			});
			addressList.add(floodAlertDTO);
		});

		return addressList;
	}

	@Override
	public FireAlertDTO getStationByAddress(String address) {
		FireAlertDTO fireAlertDTO = new FireAlertDTO();
		list.forEach(f -> {
			if (f.getAddress().equals(address)) {
				fireAlertDTO.setAddress(f.getAddress());
				fireAlertDTO.setStationNum(f.getStation());
			}
		});

		return fireAlertDTO;
	}

}
