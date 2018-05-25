package org.suurd.akamai.ccu.client.provider;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.suurd.akamai.ccu.client.CcuClientException;
import org.suurd.akamai.ccu.client.model.Configuration;

/**
 * Implementation of configuration provider that returns the configuration
 * populated from a properties file in the classpath of the application.
 * 
 * @author jsuurd
 */
public class FileConfigurationProvider implements ConfigurationProvider {

	private static final Logger LOG = LoggerFactory.getLogger(FileConfigurationProvider.class);

	/**
	 * Single globally-shared file configuration loader instance. 
	 */
	private static final FileConfigurationLoader CONFIGURATION_LOADER = new FileConfigurationLoader();

	@Override
	public Configuration getConfiguration() {
		try {
			return CONFIGURATION_LOADER.getConfiguration();
		} catch (IOException e) {
			String message = "Error loading configuration";
			LOG.error(message, e);
			throw new CcuClientException(message, e);
		}
	}

}
