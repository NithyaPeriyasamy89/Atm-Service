package com.atm.helper;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ErrorMessageProvider {

	private ResourceBundle messageBundle;

	public ErrorMessageProvider(@Value("${com.atm.errors.file}") String messageFilePath) {
		messageBundle = ResourceBundle.getBundle(messageFilePath);
	}
	
	public ResourceBundle getMessageBundle() {
		return messageBundle;
	}

	public String getErrorMessageForKey(String key) {
		return getMessageBundle().getString(key);
	}
	
}