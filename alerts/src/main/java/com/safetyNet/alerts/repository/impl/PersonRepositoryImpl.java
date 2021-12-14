package com.safetyNet.alerts.repository.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.safetyNet.alerts.dto.request.ChildAlertDTO;
import com.safetyNet.alerts.dto.request.PersonInfoDTO;
import com.safetyNet.alerts.model.Person;
import com.safetyNet.alerts.repository.PersonRepository;

@Repository
public class PersonRepositoryImpl implements PersonRepository {

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
			if (p.getCity().equals(city)) {
				mailByCity.add(p.getEmail());
			}
		});

		return mailByCity;
	}

	@Override
	public List<String> getPhoneByFireStation(List<String> addressByFireStation) {
		List<String> phoneByFireStation = new ArrayList<>();
		list.forEach(p -> {
			if (addressByFireStation.contains(p.getAddress())) {
				phoneByFireStation.add(p.getPhone());
			}
		});
		return phoneByFireStation;
	}

	@Override
	public List<PersonInfoDTO> fillMedicalRecordsByPersonDTO(List<PersonInfoDTO> medicalRecordsByPersonDTO) {

		list.forEach(p -> {
			for (PersonInfoDTO m : medicalRecordsByPersonDTO) {
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
			if (p.getAddress().equals(address)) {
				ChildAlertDTO childAlertDTO = new ChildAlertDTO();
				childAlertDTO.setFirstName(p.getFirstName());
				childAlertDTO.setLastName(p.getLastName());
				childAlertDTO.setFamily(getFamily(p));
				familyInfos.add(childAlertDTO);
			}
		});
		return familyInfos;
	}

	private List<Person> getFamily(Person child) {
		List<Person> family = new ArrayList<>();

		list.forEach(p -> {
			if (p.getAddress().equals(child.getAddress()) && !(p.getFirstName().equals(child.getFirstName())
					&& p.getLastName().equals(child.getLastName()))) {
				family.add(p);
			}
		});
		return family;

	}
}
