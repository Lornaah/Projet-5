package com.safetyNet.alerts.respository;

import org.springframework.data.repository.CrudRepository;

import com.safetyNet.alerts.model.Person;

public interface PersonRespository extends CrudRepository<Person, String> {

}
