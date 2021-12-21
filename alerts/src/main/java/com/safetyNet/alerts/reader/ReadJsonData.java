package com.safetyNet.alerts.reader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetyNet.alerts.dto.JsonListDTO;
import com.safetyNet.alerts.model.FireStation;
import com.safetyNet.alerts.model.MedicalRecords;
import com.safetyNet.alerts.model.Person;

public class ReadJsonData implements IJsonReader {

	private Resource resource = new ClassPathResource("data.json");
	private static Logger logger = LogManager.getLogger(ReadJsonData.class);

	@Override
	public JsonListDTO getObjects() throws IOException {
		if (resource == null)
			return new JsonListDTO();

		ArrayList<Person> listPersons = new ArrayList<>();
		ArrayList<FireStation> listFireStations = new ArrayList<>();
		ArrayList<MedicalRecords> listMedicalRecords = new ArrayList<>();

		ObjectMapper mapper = new ObjectMapper();

		JsonNode jsonNode = mapper.readTree(resource.getFile());

		jsonNode.get("persons").forEach(node -> addDeserializeNodeToList(listPersons, node, Person.class));
		jsonNode.get("firestations")
				.forEach(node -> addDeserializeNodeToList(listFireStations, node, FireStation.class));

		jsonNode.get("medicalrecords")
				.forEach(node -> addDeserializeNodeToList(listMedicalRecords, node, MedicalRecords.class));

		JsonListDTO jsonListDTO = new JsonListDTO(listPersons, listFireStations, listMedicalRecords);

		return jsonListDTO;
	}

	private <T> T deserialize(JsonNode node, Class<T> valueType)
			throws JsonProcessingException, IllegalArgumentException {
		ObjectMapper mapper = new ObjectMapper();
		T object = mapper.treeToValue(node, valueType);
		return object;
	}

	private <T> void addDeserializeNodeToList(List<T> list, JsonNode node, Class<T> valueType) {
		try {
			list.add(deserialize(node, valueType));
		} catch (JsonProcessingException | IllegalArgumentException e) {
			logger.error(e.getMessage());
		}
	}
}
