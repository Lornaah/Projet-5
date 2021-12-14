package com.safetyNet.alerts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.safetyNet.alerts.model.MedicalRecords;
import com.safetyNet.alerts.service.MedicalRecordsService;

@RestController
public class MedicalRecordsController {

	@Autowired
	private MedicalRecordsService medicalRecordsService;

	@GetMapping("/medicalrecords")
	public List<MedicalRecords> findAll() {
		return medicalRecordsService.findAll();
	}

	@PostMapping("/medicalrecords")
	@ResponseStatus(code = HttpStatus.CREATED)
	public void create(@RequestBody MedicalRecords medicalRecords) {
		medicalRecordsService.add(medicalRecords);
	}

	@DeleteMapping("/medicalrecords")
	@ResponseStatus(code = HttpStatus.OK)
	public void delete(@RequestBody MedicalRecords medicalRecords) {
		medicalRecordsService.delete(medicalRecords);
	}

	@PutMapping("/medicalrecords")
	@ResponseStatus(code = HttpStatus.OK)
	public void update(@RequestBody MedicalRecords medicalRecords) {
		medicalRecordsService.update(medicalRecords);
	}

}
