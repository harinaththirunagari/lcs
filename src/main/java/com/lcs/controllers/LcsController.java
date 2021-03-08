package com.lcs.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lcs.dto.LcsReqDto;
import com.lcs.dto.LcsRespDto;
import com.lcs.svc.LcsSvc;

@RestController
@RequestMapping("/lcs")
public class LcsController {

	@Autowired
	private LcsSvc lcsSvc;

	@PostMapping
	public ResponseEntity<LcsRespDto> lcs(@RequestBody LcsReqDto lcsReqDto) {
		ResponseEntity<LcsRespDto> validationResp = lcsSvc.validateRequest(lcsReqDto);
		if (null != validationResp)
			return validationResp;
		return new ResponseEntity<LcsRespDto>(lcsSvc.calculateLongestSubstring(lcsReqDto), HttpStatus.OK);
	}

}
