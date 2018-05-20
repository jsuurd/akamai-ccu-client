package org.suurd.akamai.ccu.client.mock;

import org.suurd.akamai.ccu.client.model.Configuration;
import org.suurd.akamai.ccu.client.provider.ConfigurationProvider;

public class MockConfigurationProvider implements ConfigurationProvider {

	private Configuration configuration;

	public MockConfigurationProvider(Configuration configuration) {
		this.configuration = configuration;
	}

	@Override
	public Configuration getConfiguration() {
		return configuration;
	}

}
