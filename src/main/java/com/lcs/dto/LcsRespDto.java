package com.lcs.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class LcsRespDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3996170046513670902L;

	private String error;

	private List<StringWithValue> lcs = new ArrayList<>();

	public List<StringWithValue> getLcs() {
		return lcs;
	}

	public void setLcs(List<StringWithValue> lcs) {
		this.lcs = lcs;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

}
