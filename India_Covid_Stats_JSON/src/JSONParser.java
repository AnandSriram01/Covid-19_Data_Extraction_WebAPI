import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JSONParser {

	private JsonElement jelement;
	private JsonObject jobject;
	private JsonArray jarray;
	
	public int getJsonArraySize() {
		return jarray.size();
	}
	
	public void parseJSON_States(String jsonStr, Statistics_State[] stats) {
		
		jelement = new JsonParser().parse(jsonStr);
	    jobject = jelement.getAsJsonObject();
	    jarray = jobject.getAsJsonArray("statewise");
	    JsonToJava(jarray, stats);
	}
	
	private void JsonToJava(JsonArray jarray, Statistics_State[] stats) {
		
	    int i;
	    int size = jarray.size();
	    String name;
	    int active, confirmed, recovered, deceased;
	    String lastUpdated;
	    LocalDateTime lastUpdatedFormat;
	   
	    
	    for (i=0; i<size; i++)
	    {
	    	stats[i] = new Statistics_State();
	    	
	    	name = jarray.get(i).getAsJsonObject().get("state").getAsString();
	    	confirmed = jarray.get(i).getAsJsonObject().get("confirmed").getAsInt();
	    	active = jarray.get(i).getAsJsonObject().get("active").getAsInt();
	    	recovered = jarray.get(i).getAsJsonObject().get("recovered").getAsInt();
	    	deceased = jarray.get(i).getAsJsonObject().get("deaths").getAsInt();
	    	lastUpdated = jarray.get(i).getAsJsonObject().get("lastupdatedtime").getAsString();
	    	
	    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"); 
	    	lastUpdatedFormat = LocalDateTime.parse(lastUpdated, formatter);
	    	
	    	stats[i].setName(name);
	    	stats[i].setConfirmed(confirmed);
	    	stats[i].setActive(active);
	    	stats[i].setRecovered(recovered);
	    	stats[i].setDeceased(deceased);
			stats[i].setLastUpdated(lastUpdatedFormat);
	    }
	}
	
}
