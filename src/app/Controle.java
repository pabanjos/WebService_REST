package app;

import java.util.HashMap;
import java.util.Map;

import beans.Usuario;

//@Singleton
public class Controle {
	private static Map<String, Usuario> LISTA = new HashMap<>();
	private static Usuario LOGADO = null;

	private Controle() {
		super();
	}

	public static Map<String, Usuario> getLISTA() {
		return LISTA;
	}

	public static Usuario getLOGADO() {
		return LOGADO;
	}

	public static void setLOGADO(final Usuario lOGADO) {
		LOGADO = lOGADO;
		LISTA.put(lOGADO.getLogin(), lOGADO);
	}

	public static void deslogar(final Usuario u) {
		LOGADO = null;
		LISTA.remove(u.getLogin());
	}

}
