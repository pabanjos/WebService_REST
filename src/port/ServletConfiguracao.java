package port;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.codec.binary.Base64;

@WebServlet("/configuracao")
public class ServletConfiguracao extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static String PATH = "\\posters";

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getParameter("caminho");
		PATH = path;
		response.sendRedirect("index.jsp");
	}

	public static Map<String, String> obterBytesImagens() {
		Map<String, String> mapa = new HashMap<String, String>();
		try {
			Files.walk(new File(PATH).toPath(), 2).forEach(a -> {
				final String[] vetor = a.getFileName().toString().split("\\.");
				if (a.toFile().isFile() && vetor[1].contains("jpg")) {
					byte[] bytes = null;
					try {
						bytes = Files.readAllBytes(a);
					} catch (IOException e) {
						e.printStackTrace();
					}
					mapa.put(vetor[0], Base64.encodeBase64String(bytes));
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
		return mapa;
	}

}
