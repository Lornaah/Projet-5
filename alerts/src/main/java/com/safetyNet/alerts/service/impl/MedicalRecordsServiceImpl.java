package com.safetyNet.alerts.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.safetyNet.alerts.model.MedicalRecords;
import com.safetyNet.alerts.serializer.MedicalRecordsSerializer;
import com.safetyNet.alerts.service.MedicalRecordsService;

@Service
public class MedicalRecordsServiceImpl implements MedicalRecordsService {

	@Override
	public List<MedicalRecords> findAll() {

		List<MedicalRecords> liste = new ArrayList<>();
		MedicalRecordsSerializer medicalRecordsSerializer = new MedicalRecordsSerializer();
		try {
			medicalRecordsSerializer.parse();
		} catch (IOException e) {
			e.printStackTrace();
		}

		liste.addAll(medicalRecordsSerializer.getList());
		return liste;
	}

}
