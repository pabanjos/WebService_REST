package app;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class Main {

	private static final String REST_URI = "https://api.imgbb.com/1/upload?key=1c718e4abb7fc3400069c40fb47d3a37";

	public static void main(final String[] args) {
		Data data = new Data();
		data.setImage("https://i.ibb.co/HzjvRpF/Troy-2004.jpg");
		Response cliente = ClientBuilder.newClient()//
				.target(REST_URI)//
				.request(MediaType.APPLICATION_JSON)//
				.post(Entity.entity(data, MediaType.APPLICATION_JSON));

		System.out.println(cliente.getStatus());
		System.out.println(cliente.getEntity());
	}

}

class Data {
	String image;

	public String getImage() {
		return image;
	}

	public void setImage(final String image) {
		this.image = image;
	}

}
