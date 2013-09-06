package geocoders;

import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.YahooApi;
import org.scribe.oauth.OAuthService;

import play.Play;


class GeocoderProxy {
	
	protected OAuthService service;


 	public GeocoderProxy() {
 		service = new ServiceBuilder()
		.provider(YahooApi.class)
		.apiKey(Play.application().configuration().getString("application.apiKey"))
		.apiSecret(Play.application().configuration().getString("application.apiSecret"))
		.build(); 
 	}



}