package infra;

import java.util.List;

public interface ICRUD<T> {
	public void criar(final T obj) throws Exception;

	public void criarTodos(final List<T> list) throws Exception;

	public T buscarPorId(final int id) throws Exception;

	public List<T> buscarTodos() throws Exception;

	public void alterar(final T obj) throws Exception;

	public void alterarTodos(final List<T> list) throws Exception;

	public void deletarPorId(final int id) throws Exception;

	public void deletarTodosPorIds(final int[] ids) throws Exception;
}
