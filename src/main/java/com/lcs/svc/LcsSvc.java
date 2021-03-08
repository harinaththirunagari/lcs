package com.lcs.svc;

import org.springframework.http.ResponseEntity;

import com.lcs.dto.LcsReqDto;
import com.lcs.dto.LcsRespDto;

public interface LcsSvc {

	ResponseEntity<LcsRespDto> validateRequest(LcsReqDto lcsReqDto);
	
	LcsRespDto calculateLongestSubstring(LcsReqDto lcsReqDto);
	
}
