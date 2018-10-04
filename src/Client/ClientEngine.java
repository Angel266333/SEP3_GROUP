package Client;


import java.util.ArrayList;

//Singleton class that contains all the functionality of the client
public class ClientEngine {
	private static ClientEngine me = null;
	private IRestHandler restParser;

	private ClientEngine() {
		restParser = new RestHandler("http://localhost:8001");
	}

	public static ClientEngine getInstance() {
		if(me == null) {
			me = new ClientEngine();
		}
		return me;
	}


//	Make a method for each type of action that the client should support.
//	Each method performs the REST http reuest on the server, interprets the
//	response and returns appropriate data objects.
//
//	Client and server are tied using a REST protocol

}
