package com.safetyNet.alerts.repository.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.safetyNet.alerts.model.MedicalRecords;
import com.safetyNet.alerts.repository.MedicalRecordsRepository;

@Repository
public class MedicalRecordsRepositoryImpl implements MedicalRecordsRepository {

	private final List<MedicalRecords> list = new ArrayList<>();

	@Bean
	@Scope("singleton")
	public MedicalRecordsRepositoryImpl medicalRecordsRepoSingleton() {
		return new MedicalRecordsRepositoryImpl();
	}

	@Override
	public void add(MedicalRecords element) {
		list.add(element);
	}

	@Override
	public void delete(MedicalRecords element) {
		list.remove(element);
	}

	@Override
	public void update(MedicalRecords element) {
		list.forEach(m -> {
			if (m.equals(element)) {
				m.setBirthdate(element.getBirthdate());
				m.setAllergies(element.getAllergies());
				m.setMedications(element.getMedications());
			}
		});
	}

	@Override
	public List<MedicalRecords> findAll() {
		return Collections.unmodifiableList(list);
	}

	@Override
	public void addAll(List<MedicalRecords> elements) {
		list.addAll(elements);

	}

	@Override
	public void clear() {
		list.clear();
	}

}
