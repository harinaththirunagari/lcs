package com.lcs.svc;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import com.lcs.dto.LcsReqDto;
import com.lcs.dto.LcsRespDto;
import com.lcs.dto.StringWithValue;

public class LcsSvcImplTest {

	private LcsSvcImpl lcsSvcImpl;
	
	@Before
	public void setup() {
		lcsSvcImpl = new LcsSvcImpl();
	}
	
	@Test
	public void testNullRequest() {
		Assert.assertEquals(HttpStatus.NOT_ACCEPTABLE, lcsSvcImpl.validateRequest(null).getStatusCode());
	}
	
	@Test
	public void testEmptyInputList() {
		Assert.assertEquals(HttpStatus.BAD_REQUEST, lcsSvcImpl.validateRequest(new LcsReqDto()).getStatusCode());
	}
	
	@Test
	public void testDuplicateInList() {
		LcsReqDto lcsReqDto = new LcsReqDto();
		List<StringWithValue> list = new ArrayList<>();
		StringWithValue stirngWithValue = new StringWithValue();
		stirngWithValue.setValue("comcast");
		list.add(stirngWithValue);
		StringWithValue stirngWithValue1 = new StringWithValue();
		stirngWithValue1.setValue("comcast");
		list.add(stirngWithValue1);
		lcsReqDto.setSetOfStrings(list);
		Assert.assertEquals(HttpStatus.BAD_REQUEST, lcsSvcImpl.validateRequest(lcsReqDto).getStatusCode());
	}
	
	@Test
	public void testLcs() {
		LcsReqDto lcsReqDto = new LcsReqDto();
		List<StringWithValue> list = new ArrayList<>();
		StringWithValue stirngWithValue = new StringWithValue();
		stirngWithValue.setValue("comcastghe");
		list.add(stirngWithValue);
		StringWithValue stirngWithValue1 = new StringWithValue();
		stirngWithValue1.setValue("communicateghe");
		list.add(stirngWithValue1);
		StringWithValue stirngWithValue2 = new StringWithValue();
		stirngWithValue2.setValue("commutationghe");
		list.add(stirngWithValue2);
		lcsReqDto.setSetOfStrings(list);
		LcsRespDto lcsRespDto = lcsSvcImpl.calculateLongestSubstring(lcsReqDto);
		Assert.assertEquals(2, lcsRespDto.getLcs().size());
	}
}
