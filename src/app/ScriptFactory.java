package app;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import beans.Filme;

public class ScriptFactory {
	private static final String INSERT = "INSERT INTO filme VALUES ";
	private static final String ABRE_PARENTESES = "(";
	private static final String NULL = "null";
	private static final String VIRGULA = ",";
	private static final String FECHA_PARENTESES = ")";
	private static final String PONTO_VIRGULA = ";";
	
	private static final String CAMINHO = "C:\\scripts\\";
	private static final String ARQUIVO = "filme_insert.sql";
	
	public ScriptFactory() {
		super();
	}
	
	public void gerarScript(List<Filme> filmes) {
		verificarArquivo();
		FileWriter fw = criarEscritor();
		for (Filme f : filmes) {
			String linha = criarLinha(fw, f);
			escreverLinha(fw, linha);
		}
		finalizarArquivo(fw);
	}
	
	public void verificarArquivo() {
		File diretorio = new File(CAMINHO);
		if (!diretorio.exists()) {
			diretorio.mkdir();
		}
		File arquivo = new File(CAMINHO + ARQUIVO);
		if (!arquivo.exists()) {
			try {
				arquivo.createNewFile();
			} catch (IOException e) {
				System.err.println("falha ao criar arquivo: " + e.getMessage());
			}
		}
	}

	private FileWriter criarEscritor() {
		FileWriter fw = null;
		try {
			fw = new FileWriter(CAMINHO + ARQUIVO);
		} catch (IOException e) {
			System.err.println("falha ao criar escritor: " + e.getMessage());
		}
		return fw;
	}
	
	public String criarLinha(FileWriter fw, Filme f) {
		StringBuilder sb = new StringBuilder();
		sb.append(INSERT);
		sb.append(ABRE_PARENTESES);
		sb.append(NULL).append(VIRGULA);
		sb.append(f.getPoster()).append(VIRGULA);
		sb.append(f.getTitulo()).append(VIRGULA);
		sb.append(f.getGenero()).append(VIRGULA);
		sb.append(f.getProtagonista()).append(VIRGULA);
		sb.append(f.getDiretor()).append(VIRGULA);
		sb.append(f.getLancamento());
		sb.append(FECHA_PARENTESES).append(PONTO_VIRGULA);
		sb.append(System.lineSeparator());
		return sb.toString();
	}
	
	public void escreverLinha(FileWriter fw, String linha) {
		try {			
			fw.write(linha);
		} catch (Exception e) {
			System.err.println("falha ao escrever linha: " + e.getMessage());
		}
	}

	private void finalizarArquivo(FileWriter fw) {
		try {
			fw.close();
		} catch (IOException e) {
			System.err.println("falha ao finalizar arquivo: " + e.getMessage());
		}
	}
	

}
