package controllers;

import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;

import play.*;
import play.libs.Json;
import play.mvc.*;
import views.html.*;
import geocoders.BossGeocoderProxy;
import geocoders.GeocoderResult;

public class Application extends Controller {
  
	static private BossGeocoderProxy bossGeocoder = new BossGeocoderProxy();
	
	@BodyParser.Of(BodyParser.Json.class)
    public static Result query(String q, String b) {
    	List<GeocoderResult> result = bossGeocoder.query(q, b);
    	
    	if(result == null)
    		notFound();
    	
    	response().setHeader("Access-Control-Allow-Origin", "*");
        return ok(Json.toJson(result));
    }
    
    @BodyParser.Of(BodyParser.Json.class)
    public static Result reverse(String l) {
    	GeocoderResult result = bossGeocoder.reverse(l);
    	
    	if(result == null)
    		notFound();
    	
    	response().setHeader("Access-Control-Allow-Origin", "*");
        return ok(Json.toJson(result));
    }
}