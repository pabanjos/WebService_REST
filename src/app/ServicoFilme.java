package app;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import beans.Filme;
import dao.DaoFilme;
import infra.ICRUD;
import port.ServletConfiguracao;
import servico.ServicoResposta;

public class ServicoFilme implements ICRUD<Filme> {

	private final ICRUD<Filme> dao = new DaoFilme();
	ScriptFactory fabrica = new ScriptFactory();

	public ServicoFilme() {
		super();
	}

	@Override
	public void criar(final Filme obj) throws Exception {
		dao.criar(obj);
	}

	@Override
	public void criarTodos(final List<Filme> list) throws Exception {
		throw new UnsupportedOperationException();
	}

	@Override
	public Filme buscarPorId(final int id) throws Exception {
		return dao.buscarPorId(id);
	}

	@Override
	public List<Filme> buscarTodos() throws Exception {
		final List<Filme> filmes = dao.buscarTodos();
		final Map<String, String> mapa = ServletConfiguracao.obterBytesImagens();
		filmes.forEach(f -> {
			f.setPoster(mapa.get(f.getTitulo()));
		});
		return filmes;
	}

	@Override
	public void alterar(final Filme obj) throws Exception {
		dao.alterar(obj);
	}

	@Override
	public void alterarTodos(final List<Filme> list) throws Exception {
		throw new UnsupportedOperationException();
	}

	@Override
	public void deletarPorId(final int id) throws Exception {
		dao.deletarPorId(id);
	}

	@Override
	public void deletarTodosPorIds(final int[] ids) throws Exception {
		throw new UnsupportedOperationException();
	}
	
	public void gerarScript() throws Exception {
		Filme f1 = new Filme(null, "1", "1", "1", "1", "1", 1111, 1, 1);
		Filme f2 = new Filme(null, "2", "2", "2", "2", "2", 2222, 2, 2);
		final List<Filme> lista = Arrays.asList(f1, f2);
		//buscarTodos();
		fabrica.gerarScript(lista);
	}

}
