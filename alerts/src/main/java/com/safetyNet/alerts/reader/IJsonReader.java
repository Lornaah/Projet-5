package com.safetyNet.alerts.reader;

import java.io.IOException;

import com.safetyNet.alerts.dto.JsonListDTO;

public interface IJsonReader {
	JsonListDTO getObjects() throws IOException;
}
