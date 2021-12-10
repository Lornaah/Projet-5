package com.safetyNet.alerts.repository;

import java.util.List;

public interface CrudRepository<T> {

	public void add(T element);

	public void delete(T element);

	public void update(T element);

	public List<T> findAll();

	public void addAll(List<T> elements);
}
