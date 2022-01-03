package com.safetyNet.alerts.repository.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.safetyNet.alerts.dto.request.ChildAlertDTO;
import com.safetyNet.alerts.dto.request.FireAlertDTO;
import com.safetyNet.alerts.dto.request.PersonInfoDTO;
import com.safetyNet.alerts.dto.request.fireStationInfo.FireStationInfosDTO;
import com.safetyNet.alerts.dto.request.fireStationInfo.PersonByStationNumber;
import com.safetyNet.alerts.dto.request.floodAlert.FloodAlertAddress;
import com.safetyNet.alerts.dto.request.floodAlert.FloodAlertDTO;
import com.safetyNet.alerts.dto.request.floodAlert.FloodAlertPerson;
import com.safetyNet.alerts.model.Person;
import com.safetyNet.alerts.repository.PersonRepository;

@Repository
public class PersonRepositoryImpl implements PersonRepository {

	private static Logger logger = LogManager.getLogger(PersonRepositoryImpl.class);

	private final List<Person> list = new ArrayList<>();

	@Bean
	@Scope("singleton")
	public PersonRepositoryImpl personRepoSingleton() {
		return new PersonRepositoryImpl();
	}

	@Override
	public void add(Person element) {
		list.add(element);
	}

	@Override
	public void delete(Person element) {
		list.remove(element);
	}

	@Override
	public void update(Person person) {
		list.forEach(p -> {
			if (p.equals(person)) {
				p.setAddress(person.getAddress());
				p.setCity(person.getCity());
				p.setEmail(person.getEmail());
				p.setPhone(person.getPhone());
			}
		});
	}

	@Override
	public List<Person> findAll() {
		return Collections.unmodifiableList(list);
	}

	@Override
	public void addAll(List<Person> elements) {
		list.addAll(elements);
	}

	@Override
	public void clear() {
		list.clear();
	}

	@Override
	public List<String> getCommunityMail(String city) {
		List<String> mailByCity = new ArrayList<>();
		list.forEach(p -> {
			logger.debug("determining if " + p.getCity() + " equals " + city);
			if (p.getCity().equals(city)) {

				logger.debug("adding " + p.getEmail() + " to the list");

				mailByCity.add(p.getEmail());
			}
		});

		return mailByCity;
	}

	@Override
	public List<String> getPhoneByFireStation(List<String> addressByFireStation) {
		List<String> phoneByFireStation = new ArrayList<>();
		list.forEach(p -> {

			logger.debug("determining if " + p.getAddress() + "is in addressByFireStation");

			if (addressByFireStation.contains(p.getAddress())) {

				logger.debug("adding " + p.getPhone() + " to the list");

				phoneByFireStation.add(p.getPhone());
			}
		});
		return phoneByFireStation;
	}

	@Override
	public List<PersonInfoDTO> fillMedicalRecordsByPersonDTO(List<PersonInfoDTO> medicalRecordsByPersonDTO) {
		list.forEach(p -> {
			for (PersonInfoDTO m : medicalRecordsByPersonDTO) {

				logger.debug("comparing person" + p + " to the medicalRecords" + m);

				if (p.getFirstName().equals(m.getFirstName()) && p.getLastName().equals(m.getLastName())) {
					m.setAddress(p.getAddress());
					m.setEmail(p.getEmail());
				}
			}
		});

		return medicalRecordsByPersonDTO;
	}

	@Override
	public List<ChildAlertDTO> getFamilyInfos(String address) {
		List<ChildAlertDTO> familyInfos = new ArrayList<>();

		list.forEach(p -> {

			logger.debug("determining if " + address + "is equals to " + p.getAddress());

			if (p.getAddress().equals(address)) {
				ChildAlertDTO childAlertDTO = new ChildAlertDTO();
				childAlertDTO.setFirstName(p.getFirstName());
				childAlertDTO.setLastName(p.getLastName());
				childAlertDTO.setFamily(getFamily(p));

				logger.debug("adding " + childAlertDTO + "to the list");

				familyInfos.add(childAlertDTO);
			}
		});
		return familyInfos;
	}

	private List<Person> getFamily(Person child) {
		List<Person> family = new ArrayList<>();

		list.forEach(p -> {

			logger.debug("determining if " + child.getAddress() + "is equals to " + p.getAddress() + "and if "
					+ child.getFirstName() + "is equals to " + p.getFirstName());

			if (p.getAddress().equals(child.getAddress()) && !(p.getFirstName().equals(child.getFirstName())
					&& p.getLastName().equals(child.getLastName()))) {

				logger.debug("adding " + p + "to the list");

				family.add(p);
			}
		});
		return family;

	}

	@Override
	public FireStationInfosDTO getFireStationInfos(String stationNumber, List<String> addresses) {
		FireStationInfosDTO fireStationInfos = new FireStationInfosDTO();

		fireStationInfos.setStationNumber(stationNumber);
		list.forEach(p -> {

			logger.debug("determining if " + addresses + "contains " + p.getAddress());

			if (addresses.contains(p.getAddress())) {
				PersonByStationNumber person = new PersonByStationNumber(p.getFirstName(), p.getLastName(),
						p.getAddress(), p.getPhone());

				logger.debug("adding " + person + "to " + fireStationInfos);

				fireStationInfos.addPerson(person);
			}
		});
		return fireStationInfos;
	}

	@Override
	public List<FloodAlertDTO> getPersonByAddress(List<FloodAlertDTO> floodAlertDTOList) {
		floodAlertDTOList.forEach(f -> {

			f.getAddressList().forEach(a -> fillListPerson(a));

			logger.debug("adding each elements of " + f.getAddressList() + " to the list");

		});
		return floodAlertDTOList;
	}

	private void fillListPerson(FloodAlertAddress floodAlertAddress) {
		list.forEach(l -> {

			logger.debug("determining if " + l.getAddress() + "is equals to " + floodAlertAddress.getAddress());

			if (l.getAddress().equals(floodAlertAddress.getAddress())) {
				FloodAlertPerson person = new FloodAlertPerson();
				person = createPerson(l);

				logger.debug("adding " + person + "to " + floodAlertAddress);

				floodAlertAddress.addPerson(person);
			}
		});
	}

	@Override
	public FireAlertDTO fillFirePersonInfo(FireAlertDTO firePersonInfo) {
		list.forEach(p -> {

			logger.debug("determining if " + p.getAddress() + "is equals to " + firePersonInfo.getAddress());

			if (p.getAddress().equals(firePersonInfo.getAddress())) {
				FloodAlertPerson person = new FloodAlertPerson();
				person = createPerson(p);

				logger.debug("adding " + person + "to " + firePersonInfo);

				firePersonInfo.addPerson(person);
			}
		});
		return firePersonInfo;
	}

	private FloodAlertPerson createPerson(Person p) {
		FloodAlertPerson person = new FloodAlertPerson();
		person.setFirstName(p.getFirstName());
		person.setLastName(p.getLastName());
		person.setPhoneNumber(p.getPhone());
		return person;
	}
}
