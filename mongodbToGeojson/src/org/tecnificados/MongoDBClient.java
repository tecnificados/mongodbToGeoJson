package org.tecnificados;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.UnknownHostException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

public class MongoDBClient {

	private static final String OUTPUT_FOLDER = "ouput";

	final static Logger log = Logger.getLogger(MongoDBClient.class);

	private MongoClient mongo;
	private String server;
	private String database;
	private String usuario;
	private String password;

	public MongoDBClient() {
		super();
		database = "";
		usuario = "";
		password = "";
	}

	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}

	public String getDatabase() {
		return database;
	}

	public void setDatabase(String database) {
		this.database = database;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void initClient() {
		try {

			MongoCredential credential = MongoCredential.createCredential(usuario, database, password.toCharArray());

			this.mongo = new MongoClient(new ServerAddress(server), Arrays.asList(credential));

		} catch (UnknownHostException e) {
			log.error("Error init mongodb client", e);
		}

	}

	public void extractCollection(String collection) {
		DB db = mongo.getDB(database);
		DBCollection table = db.getCollection(collection);

		BasicDBObject municipio = new BasicDBObject();

		DBCursor cursor = table.find(municipio);

		ArrayList<DBObject> lista = new ArrayList<DBObject>();
		
		ObjectMapper mapper = new ObjectMapper();


		while (cursor.hasNext()) {
			DBObject temp = cursor.next();
			log.info(temp.get("name") + "," + temp.get("poblacion"));
			
			Municipio m=new Municipio();
			m.setNombre((String) temp.get("name") );
			m.setNombreNormalizado((String) temp.get("orderName") );
			m.setGeometry((String) temp.get("geometry"));
			
			m.setLat( (double) temp.get("latitud"));
			m.setLon((double) temp.get("longitud"));
			m.setLimit((ArrayList<Integer>) temp.get("limita"));
			m.setNatCode((String) temp.get("natcode"));
			m.setIdAutonomia((Integer) temp.get("idAutonomia"));
			m.setIdProvincia((Integer) temp.get("idProvincia"));
			m.setIdPais((Integer) temp.get("idPais"));
			m.setIdMunicipio((Integer) temp.get("idMunicipio"));
			m.setPoblacion((Integer) temp.get("poblacion"));
			
			String jsonInString="";
			try {
				jsonInString = mapper.writeValueAsString(m);
				jsonInString = toPrettyFormat(jsonInString);
			} catch (Exception e1) {
				log.error("Error generating JSON Object",e1);
			}
			
			try {
				String fileName=m.getNombreNormalizado();
				if (fileName.contains("/"))
					fileName=fileName.replace("/"," - ");
				
				FileUtils.writeStringToFile(new File(OUTPUT_FOLDER+File.separator+m.getIdPais()+File.separator+fileName+".json"), jsonInString, "UTF-8");
			} catch (IOException e) {
				log.error("Error writting file",e);
			}
		}

		

	}

	
	public static String toPrettyFormat(String jsonString) {
		JsonParser parser = new JsonParser();
		JsonObject json = parser.parse(jsonString).getAsJsonObject();

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String prettyJson = gson.toJson(json);

		return prettyJson;
	}

	
}
