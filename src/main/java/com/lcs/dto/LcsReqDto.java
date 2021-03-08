package com.lcs.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class LcsReqDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3663482723304576243L;
	
	private List<StringWithValue> setOfStrings = new ArrayList<>();

	public List<StringWithValue> getSetOfStrings() {
		return setOfStrings;
	}

	public void setSetOfStrings(List<StringWithValue> setOfStrings) {
		this.setOfStrings = setOfStrings;
	}
	
}
