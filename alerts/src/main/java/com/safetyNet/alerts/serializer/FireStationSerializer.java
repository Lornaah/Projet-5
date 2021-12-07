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
import com.safetyNet.alerts.model.FireStation;

public class FireStationSerializer {
	private List<FireStation> fireStations = new ArrayList<>();

	public void parse() throws IOException {
		Resource resource = new ClassPathResource("data.json");
		ObjectMapper mapper = new ObjectMapper();

		JsonNode jsonNode = mapper.readTree(resource.getFile());
		jsonNode.get("firestations").forEach(node -> {
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
		FireStation fireStation = mapper.treeToValue(node, FireStation.class);
		fireStations.add(fireStation);
	}

	public List<FireStation> getList() {
		return Collections.unmodifiableList(fireStations);
	}

}
