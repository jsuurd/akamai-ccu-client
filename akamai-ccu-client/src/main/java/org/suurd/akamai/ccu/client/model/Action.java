package org.suurd.akamai.ccu.client.model;

import com.google.api.client.util.NullValue;
import com.google.api.client.util.Value;

public enum Action {

	@Value("invalidate")
	INVALIDATE,

	@NullValue
	@Value("remove")
	REMOVE

}
