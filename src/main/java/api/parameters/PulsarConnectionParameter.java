package api.parameters;

import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;

public class PulsarConnectionParameter {

	@Parameter
	@DisplayName("Host")
	private String host;
	
	@Parameter
	@DisplayName("Port")
	private String port;
	
	

	public String getHost() {
		return host;
	}



	public String getPort() {
		return port;
	}



	public String getBootstrapServer() {
		return "pulsar://" + host + ":" + "port";
	}
}