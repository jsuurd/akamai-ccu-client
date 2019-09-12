package org.suurd.akamai.ccu.client.provider;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.suurd.akamai.ccu.client.model.Configuration;

public class FileConfigurationLoaderTests {

	private FileConfigurationLoader configurationLoader;

	@Before
	public void setUp() throws Exception {
		configurationLoader = new FileConfigurationLoader();
	}

	@Test
	public void getConfiguration_ShouldReturnPropertiesFromFile() throws IOException {
		Configuration configuration = configurationLoader.getConfiguration();

		assertEquals("test-base-authority", configuration.getBaseAuthority());
		assertEquals("test-access-token", configuration.getAccessToken());
		assertEquals("test-client-token", configuration.getClientToken());
		assertEquals("test-client-secret", configuration.getClientSecret());
		assertEquals("/ccu/v2/queues", configuration.getQueuesEndpoint());
		assertEquals(Integer.valueOf(5000), configuration.getConnectTimeout());
		assertEquals(Integer.valueOf(10000), configuration.getReadTimeout());
		assertEquals(Integer.valueOf(3), configuration.getNumberOfRetries());
	}

}
