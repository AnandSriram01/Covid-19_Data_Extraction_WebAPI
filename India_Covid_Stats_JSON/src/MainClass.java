import java.io.IOException;

public class MainClass {

	private static final String URL_Stat = "https://api.covid19india.org/data.json";
	
	public static void main(String[] args) throws IOException, InterruptedException  {
		
		HTTPClientRequest HttpObj = new HTTPClientRequest();		
		String jsonStr = HttpObj.retrieveResponse(URL_Stat);

		Statistics_State[] stats = new Statistics_State[40];
		
		JSONParser JObj = new JSONParser();	
		JObj.parseJSON_States(jsonStr,stats);
		
		int size = JObj.getJsonArraySize();
	    
	    System.out.printf("%50s %10s %10s %10s %10s","STATE","CONFIRMED","ACTIVE","RECOVERED","DECEASED");
	    System.out.println("\n________________________________________________________________________________________________________________________\n");
	    
	    for (int i = 0; i < size ; i++)
	    	stats[i].displayData();
	    
	    JavaToExcel ExcelObj = new JavaToExcel();	
//	    ExcelObj.writeFile(stats,size);
	    ExcelObj.activeCases(stats,size);
		
	}
}
