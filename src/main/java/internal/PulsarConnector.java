package internal;
import org.mule.runtime.extension.api.annotation.Extension;
import org.mule.runtime.extension.api.annotation.connectivity.ConnectionProviders;
import org.mule.runtime.extension.api.annotation.dsl.xml.Xml;

import internal.connection.PulsarConnectionProvider;

@Extension(name = "Apache Pulsar")
@Xml(prefix = "pulsar")
@ConnectionProviders(PulsarConnectionProvider.class)

public class PulsarConnector {

}
