package app;

import java.util.List;

import beans.Carrinho;
import beans.Compra;
import beans.Filme;
import beans.Log;
import servico.ServicoResposta;

public class ServicoCarrinho {

	private final ServicoCompra servicoCompra = new ServicoCompra();
	private final Carrinho carrinho = new Carrinho();

	public ServicoCarrinho() {
		super();
	}

	public Carrinho obter() {
		try {
			Integer idUsuario = CacheUsuario.getLOGADO().getIdUsuario();
			List<Compra> compras = servicoCompra.obterComprasPendentesDoUsuario(idUsuario);
			carrinho.setCompras(compras);
			carrinho.calcular();
		} catch (Exception e) {
			ServicoResposta.adicionarLog(Log.falha("falha ao montar carrinho."));
			e.printStackTrace();
		}
		return carrinho;
	}

	public Carrinho adicionar(final Filme filme) {
		int idFilme = filme.getIdFilme();
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
		return carrinho;
	}

	public Carrinho remover(final Filme filme) {
		int idFilme = filme.getIdFilme();
		Compra itemCarrinho = carrinho.obter(idFilme);
		if (itemCarrinho.getQuantidade() > 1) {
			itemCarrinho.setQuantidade(itemCarrinho.getQuantidade() - 1);
		} else {
			carrinho.getCompras().remove(itemCarrinho);
		}
		carrinho.calcular();
		return carrinho;
	}

}