package uk.org.tdn.finances.service;

import java.util.List;

public interface IAbstractService<T, PK> {

	public List<T> findAll();

	public T findById(PK id);

	public T save(T entity);

	public void remove(T entity);

	public int count();

}