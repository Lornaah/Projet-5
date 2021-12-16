package com.safetyNet.alerts.ITest;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class ControllerIntegrationTest {

	@Autowired
	private MockMvc mvc;

	@Test
	public void communityEmailTest() throws Exception {
		mvc.perform(get("/communityEmail?city=Culver")).andExpect(status().isOk()).andExpect(jsonPath("$").exists())
				.andExpect(jsonPath("$").isArray()).andExpect(jsonPath("$[0]", is("jaboyd@email.com")));
	}

	@Test
	public void phoneAlertTest() throws Exception {
		mvc.perform(get("/phoneAlert?firestation=1")).andExpect(status().isOk()).andExpect(jsonPath("$").exists())
				.andExpect(jsonPath("$").isArray()).andExpect(jsonPath("$[0]", is("841-874-6512")));
	}

	@Test
	public void personInfoTest() throws Exception {
		mvc.perform(get("/personInfo?firstName=John&lastName=Boyd")).andExpect(status().isOk())
				.andExpect(jsonPath("$").exists()).andExpect(jsonPath("$").isArray())
				.andExpect(jsonPath("$[0].firstName", is("John"))).andExpect(jsonPath("$[0].lastName", is("Boyd")))
				.andExpect(jsonPath("$[0].address", is("1509 Culver St"))).andExpect(jsonPath("$[0].age", is(37)))
				.andExpect(jsonPath("$[0].email", is("jaboyd@email.com")))
				.andExpect(jsonPath("$[0].medications[0]", is("aznol:350mg")))
				.andExpect(jsonPath("$[0].medications[1]", is("hydrapermazol:100mg")))
				.andExpect(jsonPath("$[0].allergies[0]", is("nillacilan")));
	}

	@Test
	public void childAlertTest() throws Exception {
		mvc.perform(get("/childAlert?address=1509 Culver St")).andExpect(status().isOk())
				.andExpect(jsonPath("$").exists()).andExpect(jsonPath("$").isArray())
				.andExpect(jsonPath("$[0].firstName", is("Tenley"))).andExpect(jsonPath("$[0].lastName", is("Boyd")))
				.andExpect(jsonPath("$[0].age", is(9))).andExpect(jsonPath("$[0].family[0].firstName", is("John")))
				.andExpect(jsonPath("$[0].family[0]").exists());

	}

	@Test
	public void personByStationNumberTest() throws Exception {
		mvc.perform(get("/firestation?stationNumber=3")).andExpect(status().isOk()).andExpect(jsonPath("$").exists())
				.andExpect(jsonPath("$.stationNumber", is("3")))
				.andExpect(jsonPath("$.infoPerson[0].firstName", is("John")))
				.andExpect(jsonPath("$.infoPerson[1].phoneNumber", is("841-874-6513")));
	}

	@Test
	public void floodAlertTest() throws Exception {
		mvc.perform(get("/flood/stations?stations=1,2")).andExpect(status().isOk()).andExpect(jsonPath("$").exists())
				.andExpect(jsonPath("$[0].stationNum", is("1")))
				.andExpect(jsonPath("$[0].addressList[0].address", is("644 Gershwin Cir")))
				.andExpect(jsonPath("$[0].addressList[0].listPerson[0].firstName", is("Peter")))
				.andExpect(jsonPath("$[0].addressList[0].listPerson[0].lastName", is("Duncan")))
				.andExpect(jsonPath("$[0].addressList[0].listPerson[0].allergies[0]", is("shellfish")))
				.andExpect(jsonPath("$[0].addressList[0].listPerson[0].phoneNumber", is("841-874-6512")));
	}

	@Test
	public void fireAlertTest() throws Exception {
		mvc.perform(get("/fire?address=1509 Culver St")).andExpect(status().isOk()).andExpect(jsonPath("$").exists())
				.andExpect(jsonPath("$.address", is("1509 Culver St"))).andExpect(jsonPath("$.stationNum", is("3")))
				.andExpect(jsonPath("$.listPerson[0].firstName", is("John")))
				.andExpect(jsonPath("$.listPerson[0].lastName", is("Boyd")));

	}
}
