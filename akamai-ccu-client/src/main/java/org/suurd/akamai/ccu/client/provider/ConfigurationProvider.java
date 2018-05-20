package org.suurd.akamai.ccu.client.provider;

import org.suurd.akamai.ccu.client.model.Configuration;

/**
 * Interface providing an implementation of the CCU client configuration.
 * 
 * @author jsuurd
 */
public interface ConfigurationProvider {

	/**
	 * Gets the configuration.
	 * 
	 * @return the configuration
	 */
	Configuration getConfiguration();

}
