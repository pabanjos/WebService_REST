package dao;

import java.util.List;

public interface ICRUD<T> {
	public void create(final T obj) throws Exception;

	public void createAll(final List<T> list) throws Exception;

	public T readById(final int id) throws Exception;

	public List<T> readAll() throws Exception;

	public void update(final T obj) throws Exception;

	public void updateAll(final List<T> list) throws Exception;

	public void deleteById(final int id) throws Exception;

	public void deleteAllById(final int[] ids) throws Exception;
}
