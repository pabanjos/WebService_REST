package dao;

import java.util.List;

public interface IDao<T> {

	public void create(T obj) throws Exception;

	public T readById(int id) throws Exception;

	public List<T> read() throws Exception;

	public void update(T obj) throws Exception;

	public void deleteById(int id) throws Exception;

}
