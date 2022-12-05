package internal.connection;

import java.net.InetSocketAddress;
import java.net.Socket;

import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.mule.runtime.api.connection.ConnectionException;
import org.mule.runtime.api.connection.ConnectionProvider;
import org.mule.runtime.api.connection.ConnectionValidationResult;
import org.mule.runtime.extension.api.annotation.param.ParameterGroup;

import api.connection.PulsarConnection;
import api.parameters.PulsarConnectionParameter;

public class PulsarConnectionProvider implements ConnectionProvider<PulsarConnection> {

	@ParameterGroup(name = "Connection")
	PulsarConnectionParameter pulsarConnectionParameter;

	@Override
	public PulsarConnection connect() throws ConnectionException {

		try {
			return new PulsarConnection(pulsarConnectionParameter);
		} catch (PulsarClientException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public void disconnect(PulsarConnection connection) {

		try {
			connection.invalidate();
		} catch (PulsarClientException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ConnectionValidationResult validate(PulsarConnection arg0) {

		try (Socket socket = new Socket()) {
			socket.connect(new InetSocketAddress(pulsarConnectionParameter.getHost(),
					Integer.valueOf(pulsarConnectionParameter.getPort())), 10000);
		}catch(Exception e) {
			return ConnectionValidationResult.failure("Failed to create Apache Pulsar Client Connection", new Exception(e.getMessage()));
		}
		// Check whether PulsarClient is alive
		return ConnectionValidationResult.success();
	}
}