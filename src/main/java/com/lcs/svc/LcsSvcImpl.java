package com.lcs.svc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lcs.dto.LcsReqDto;
import com.lcs.dto.LcsRespDto;
import com.lcs.dto.StringWithValue;

@Service
public class LcsSvcImpl implements LcsSvc {

	@Override
	public ResponseEntity<LcsRespDto> validateRequest(LcsReqDto lcsReqDto) {
		ResponseEntity<LcsRespDto> lcsResponseEntity = null;
		LcsRespDto resBody = new LcsRespDto();

		if (isNullRequest(lcsReqDto)) {
			resBody.setError("Incorrect request");
			lcsResponseEntity = new ResponseEntity<LcsRespDto>(resBody, HttpStatus.NOT_ACCEPTABLE);
		} else if (isEmptyList(lcsReqDto)) {
			resBody.setError("setOfStrings should not be empty");
			lcsResponseEntity = new ResponseEntity<LcsRespDto>(resBody, HttpStatus.BAD_REQUEST);
		} else if (isDuplicatesInList(lcsReqDto)) {
			resBody.setError("setOfStrings must be a Set (i.e. all strings must be unique)");
			lcsResponseEntity = new ResponseEntity<LcsRespDto>(resBody, HttpStatus.BAD_REQUEST);
		}

		return lcsResponseEntity;
	}

	private boolean isDuplicatesInList(LcsReqDto lcsReqDto) {
		List<StringWithValue> setOfStrings = lcsReqDto.getSetOfStrings();
		List<String> listOfStrs = new ArrayList<>();
		Set<String> setOfStrs = new HashSet<>();
		setOfStrings.forEach(strWithValue -> {
			listOfStrs.add(strWithValue.getValue());
			setOfStrs.add(strWithValue.getValue());
		});
		return listOfStrs.size() != setOfStrs.size();
	}

	@Override
	public LcsRespDto calculateLongestSubstring(LcsReqDto lcsReqDto) {
		LcsRespDto lcsRespDto = new LcsRespDto();
		List<StringWithValue> res = new ArrayList<>();
		lcsRespDto.setLcs(res);
		List<String> longestSubStrings = getLongestSubString(lcsReqDto.getSetOfStrings());
		sortListInDescByStrLength(longestSubStrings);
		String longestSubstr = longestSubStrings.get(0);
		int longestStrLength = longestSubstr.length();
		addSubStrToList(longestSubstr, res);
		longestSubStrings.remove(0);
		for (String subStr: longestSubStrings) {
			if (subStr.length() == longestStrLength)
				addSubStrToList(subStr, res);
		}
		return lcsRespDto;
	}

	private void sortListInDescByStrLength(List<String> longestSubStrings) {
		Collections.sort(longestSubStrings, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o2.length() - o1.length();
			}
		});
	}

	private void addSubStrToList(String substr, List<StringWithValue> strWithValueList) {
		StringWithValue str = new StringWithValue();
		str.setValue(substr);
		strWithValueList.add(str);
	}

	private boolean isEmptyList(LcsReqDto lcsReqDto) {
		return lcsReqDto.getSetOfStrings().isEmpty();
	}

	private boolean isNullRequest(LcsReqDto lcsReqDto) {
		return null == lcsReqDto;
	}

	private List<String> getLongestSubString(List<StringWithValue> inputValues) {
		List<String> respValues = new ArrayList<>();
		sortInDescOrder(inputValues);
		String longestString = inputValues.get(0).getValue();
		inputValues.remove(0);

		for (int i = 0; i <= longestString.length(); i++) {
			for (int j = i; j <= longestString.length(); j++) {
				String subStr = longestString.substring(i, j);
				if (checkIfValidSubstr(subStr, inputValues) && !respValues.contains(subStr)) {
					respValues.add(subStr);
				}
			}
		}
		return respValues;
	}

	private void sortInDescOrder(List<StringWithValue> inputValues) {
		Collections.sort(inputValues, new Comparator<StringWithValue>() {
			@Override
			public int compare(StringWithValue o1, StringWithValue o2) {
				return o2.getValue().length() - o1.getValue().length();
			}
		});
	}

	private boolean checkIfValidSubstr(String subStr, List<StringWithValue> values) {
		AtomicBoolean valid = new AtomicBoolean(true);
		values.forEach(value -> {
			if (!value.getValue().contains(subStr))
				valid.set(false);
			;
		});

		return valid.get();
	}
}
