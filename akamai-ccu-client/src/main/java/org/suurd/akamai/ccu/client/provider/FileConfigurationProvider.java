package org.suurd.akamai.ccu.client.provider;

import org.suurd.akamai.ccu.client.model.Configuration;

/**
 * Implementation of configuration provider that returns the configuration
 * populated from a properties file in the classpath of the application.
 * 
 * @author jsuurd
 */
public class FileConfigurationProvider implements ConfigurationProvider {

	/**
	 * Single globally-shared file configuration loader instance. 
	 */
	private static final FileConfigurationLoader CONFIGURATION_LOADER = new FileConfigurationLoader();

	@Override
	public Configuration getConfiguration() {
		return CONFIGURATION_LOADER.getConfiguration();
	}

}
