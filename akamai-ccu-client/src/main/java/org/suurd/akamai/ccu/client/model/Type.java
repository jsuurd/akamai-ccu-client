package org.suurd.akamai.ccu.client.model;

import com.google.api.client.util.NullValue;
import com.google.api.client.util.Value;

public enum Type {

	@NullValue
	@Value("arl")
	ARL,

	@Value("cpcode")
	CPCODE

}
