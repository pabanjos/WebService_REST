package app;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import beans.Compra;

public class ControleCarrinho {

	private final ServicoCompra servicoCompra = new ServicoCompra();
	private Map<Integer, Compra> carrinho = null;

	public ControleCarrinho() {
		super();
		montarCarrinho();
	}

	private void montarCarrinho() {
		try {
			if (carrinho == null) {
				Integer idUsuario = Controle.getLOGADO().getIdUsuario();
				List<Compra> lista = servicoCompra.obterComprasPendentesDoUsuario(idUsuario);
				carrinho = lista.stream().collect(//
						Collectors.toMap(c -> c.getFilme().getIdFilme(), Function.identity()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}