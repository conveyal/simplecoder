package geocoders;

import java.io.BufferedReader;  
import java.io.IOException;  
import java.io.InputStreamReader;  
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;  
import java.net.MalformedURLException;  
import java.net.URL;  
 
  
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import oauth.signpost.OAuthConsumer;  
import oauth.signpost.basic.DefaultOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;  
import oauth.signpost.exception.OAuthExpectationFailedException;  
import oauth.signpost.exception.OAuthMessageSignerException;  
import oauth.signpost.http.HttpRequest;
import play.Logger;
import play.Play;
  

public class BossGeocoderProxy {  
    
	private static ObjectMapper mapper = new ObjectMapper();
	
	
    public List<GeocoderResult> query(String query, String bound) {    	
    	URL url;
    	OAuthConsumer consumer = new DefaultOAuthConsumer(Play.application().configuration().getString("application.apiKey"),
				Play.application().configuration().getString("application.apiSecret"));
    	
		try {
			query = query.replace(" ", "+").trim();
			bound = bound.replace(" ", "+").trim();
			
			String location = ""; 
			
			if(bound.isEmpty())
				location = URLEncoder.encode(query, "UTF-8");
			else
				location = URLEncoder.encode(query  + ",+" + bound, "UTF-8");
				
			Logger.info(location);
			
			url = new URL("http://yboss.yahooapis.com/geo/placefinder?flags=J&location=" + location);
			
			Logger.info(url.toString());
			HttpURLConnection urlConnection;
			try {
				urlConnection = (HttpURLConnection) url.openConnection();
				
				try {
					HttpRequest request = consumer.sign(urlConnection);
					
					urlConnection.connect();  
				     
			         int responseCode = urlConnection.getResponseCode();  
			         
			         StringBuffer stringBuffer = new StringBuffer();  
			         
			         if(responseCode == 200) {  
			             BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));  
			             
			             String line;  
			             while ((line = reader.readLine()) != null) {  
			            	 stringBuffer.append(line);  
			             }  
			             reader.close();
			             
			             Logger.info(stringBuffer.toString());
			             
			             JsonNode bossResponse = mapper.readValue(stringBuffer.toString(), JsonNode.class);
			             
			             JsonNode results = bossResponse.findValue("results");
			             
			             if(results != null && results.isArray())
			             {
			            	 ArrayList<GeocoderResult> responseList = new ArrayList<GeocoderResult>();
			            	 
			            	 for(JsonNode result : results){
			            		 
			            		 GeocoderResult response = new GeocoderResult();
			            		 response.address = result.get("line1").getTextValue();
			            		 response.city = result.get("city").getTextValue();
			            		 response.state = result.get("state").getTextValue();
			            		 response.lat = Double.parseDouble(result.get("latitude").getTextValue());
			            		 response.lon = Double.parseDouble(result.get("longitude").getTextValue());
			            		 
			            		 responseList.add(response);
			            		 
			            	 }
			            	 
			            	 return responseList;
			             }
			             else
			            	 return null;
			             
			         }
			         
			         Logger.info(stringBuffer.toString());
					
				} catch (OAuthMessageSignerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (OAuthExpectationFailedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (OAuthCommunicationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
		         
					
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
	         		
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	 
        
    	
         return null;
    }
    
    
    public GeocoderResult reverse(String latlng) {    	
    	URL url;
    	OAuthConsumer consumer = new DefaultOAuthConsumer(Play.application().configuration().getString("application.apiKey"),
				Play.application().configuration().getString("application.apiSecret"));
    	
		try {
			latlng = latlng.replace(" ", "+").trim();
			
			String location = ""; 
			
			location = URLEncoder.encode(latlng, "UTF-8");
				
			Logger.info(location);
			
			url = new URL("http://yboss.yahooapis.com/geo/placefinder?gflags=R&flags=J&location=" + location);
			
			Logger.info(url.toString());
			HttpURLConnection urlConnection;
			try {
				urlConnection = (HttpURLConnection) url.openConnection();
				
				try {
					HttpRequest request = consumer.sign(urlConnection);
					
					urlConnection.connect();  
				     
			         int responseCode = urlConnection.getResponseCode();  
			         
			         StringBuffer stringBuffer = new StringBuffer();  
			         
			         if(responseCode == 200) {  
			             BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));  
			             
			             String line;  
			             while ((line = reader.readLine()) != null) {  
			            	 stringBuffer.append(line);  
			             }  
			             reader.close();
			             
			             Logger.info(stringBuffer.toString());
			             
			             JsonNode bossResponse = mapper.readValue(stringBuffer.toString(), JsonNode.class);
			             
			             JsonNode results = bossResponse.findValue("results");
			             
			             if(results != null && results.isArray())
			             {
			            	 for(JsonNode result : results){
			            		 
			            		 GeocoderResult response = new GeocoderResult();
			            		 response.address = result.get("line1").getTextValue();
			            		 response.city = result.get("city").getTextValue();
			            		 response.state = result.get("state").getTextValue();
			            		 response.lat = Double.parseDouble(result.get("latitude").getTextValue());
			            		 response.lon = Double.parseDouble(result.get("longitude").getTextValue());
			            		 
			            		 return response;
			            	 }
			             }
			             else
			            	 return null;
			             
			         }
			         
			         Logger.info(stringBuffer.toString());
					
				} catch (OAuthMessageSignerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (OAuthExpectationFailedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (OAuthCommunicationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
		         
					
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
	         		
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	 
        
    	
         return null;
    }
}