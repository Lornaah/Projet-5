package com.safetyNet.alerts.serializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetyNet.alerts.model.MedicalRecords;

public class MedicalRecordsSerializer {
	private List<MedicalRecords> medicalRecordsList = new ArrayList<>();

	public void parse() throws IOException {
		Resource resource = new ClassPathResource("data.json");
		ObjectMapper mapper = new ObjectMapper();

		JsonNode jsonNode = mapper.readTree(resource.getFile());
		jsonNode.get("medicalrecords").forEach(node -> {
			try {
				deserialize(node);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			}
		});
	}

	private void deserialize(JsonNode node) throws JsonProcessingException, IllegalArgumentException {

		ObjectMapper mapper = new ObjectMapper();
		MedicalRecords medicalRecords = mapper.treeToValue(node, MedicalRecords.class);
		medicalRecordsList.add(medicalRecords);
	}

	public List<MedicalRecords> getList() {
		return Collections.unmodifiableList(medicalRecordsList);
	}
}