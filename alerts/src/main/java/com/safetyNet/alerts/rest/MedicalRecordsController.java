package com.safetyNet.alerts.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.safetyNet.alerts.model.MedicalRecords;
import com.safetyNet.alerts.service.MedicalRecordsService;

@RestController
@RequestMapping("/medicalrecords")
public class MedicalRecordsController {

	@Autowired
	private MedicalRecordsService medicalRecordsService;

	@GetMapping
	public List<MedicalRecords> findAll() {
		return medicalRecordsService.findAll();
	}

}
