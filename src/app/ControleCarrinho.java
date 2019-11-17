package app;

import java.util.List;

import beans.Carrinho;
import beans.Compra;
import beans.Filme;
import beans.Log;
import servico.ServicoResposta;

public class ControleCarrinho {

	private final ServicoCompra servicoCompra = new ServicoCompra();
	private final Carrinho carrinho = new Carrinho();

	public ControleCarrinho() {
		super();
		montarCarrinho();
	}

	private void montarCarrinho() {
		try {
			Integer idUsuario = CacheUsuario.getLOGADO().getIdUsuario();
			List<Compra> compras = servicoCompra.obterComprasPendentesDoUsuario(idUsuario);
			carrinho.setCompras(compras);
		} catch (Exception e) {
			ServicoResposta.adicionarLog(Log.falha("falha ao montar carrinho."));
			e.printStackTrace();
		}
	}

	public void adicionar(final Filme filme) {
		Integer idFilme = filme.getIdFilme();
		if (carrinho.contem(idFilme)) {
			Compra itemCarrinho = carrinho.obter(idFilme);
			itemCarrinho.setQuantidade(itemCarrinho.getQuantidade() + 1);
		} else {
			Compra compra = new Compra();
			compra.setFilme(filme);
			compra.setQuantidade(1);
			compra.setUsuario(CacheUsuario.getLOGADO());
			carrinho.getCompras().add(compra);
		}
		carrinho.calcular();
	}

	public void remover(final Filme filme) {
		Integer idFilme = filme.getIdFilme();
		Compra itemCarrinho = carrinho.obter(idFilme);
		if (itemCarrinho.getQuantidade() > 1) {
			itemCarrinho.setQuantidade(itemCarrinho.getQuantidade() - 1);
		} else {
			carrinho.getCompras().remove(itemCarrinho);
		}
		carrinho.calcular();
	}

}