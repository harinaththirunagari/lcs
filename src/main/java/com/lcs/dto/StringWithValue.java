package com.lcs.dto;

import java.io.Serializable;

public class StringWithValue implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4308180983964469638L;
	
	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
