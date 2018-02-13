package com.aml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.ObjectUtils;

import com.alibaba.fastjson.JSON;
import com.aml.api.dto.AssignRuleDTO;
import com.aml.api.dto.DicTbDTO;
import com.aml.api.dto.FieldDTO;
import com.aml.api.dto.NoticeRuleDTO;
import com.aml.api.dto.TaskScoreDTO;

public class TestConfig {
	public static void main ( String[] args ) {
		// insertDicTb();
		// insertRule();

		TaskScoreDTO dto = new TaskScoreDTO();
		dto.setAlertId(12L);
		dto.setUserName("admin");
		dto.setGrade(2);
		Map<String, Object> w = new HashMap<String, Object>();
		w.put("sarItem", "aaa");
		w.put("actionRequired", "bbb");
		w.put("actionToken", "ccc");
		w.put("corrected", "Y");
		dto.setOrganizedWritten(JSON.toJSONString(w));

		Map<String, Object> a = new HashMap<String, Object>();
		a.put("sarItem", "ddd");
		a.put("actionRequired", "eee");
		a.put("actionToken", "fff");
		a.put("corrected", "N");
		dto.setAccuratelyAppropriately(JSON.toJSONString(a));

		System.out.println(JSON.toJSONString(dto));

		List<String> alertFIU = new ArrayList<String>();
		alertFIU.add("a");
		alertFIU.add("b");
		alertFIU.add("c");
		alertFIU.add("d");
		alertFIU.add("e");
		
		NoticeRuleDTO notice = new NoticeRuleDTO();
		notice.setWarnType(1);
		notice.setRemindType("before");
		notice.setDays(4);
		notice.setNum(4);
		notice.setIntervalDays(4);
		System.out.println(notice.toJson());

	}
	
	public static boolean isLetter(char c) {   
	       int k = 0x80;   
	       return c / k == 0 ? true : false;   
	   }  

	public static void insertDicTb () {
		FieldDTO dto = new FieldDTO();
		dto.setDicId(6L);

		List<DicTbDTO> dicTbs = new ArrayList<DicTbDTO>();
		DicTbDTO tb1 = new DicTbDTO();
		tb1.setDicSid(34L);
		tb1.setSortIndex(1);
		tb1.setUserId(123456L);
		DicTbDTO tb2 = new DicTbDTO();
		tb2.setDicSid(36L);
		tb2.setSortIndex(2);
		tb2.setUserId(123456L);
		dicTbs.add(tb1);
		dicTbs.add(tb2);

		dto.setDicTbs(dicTbs);
		System.out.println(JSON.toJSONString(dto));
	}

	public static void insertRule () {
		AssignRuleDTO dto = new AssignRuleDTO();
		dto.setRoleId(10043L);
		dto.setRuleType(1);

		// List<AssignRuleItemDTO> ruleItems = new ArrayList<AssignRuleItemDTO>();
		// AssignRuleItemDTO rule1 = new AssignRuleItemDTO();
		// rule1.setStartValue(0);
		// rule1.setEndValue(5);
		// rule1.setThreshold(40);
		// AssignRuleItemDTO rule2 = new AssignRuleItemDTO();
		// rule2.setStartValue(5);
		// rule2.setEndValue(10);
		// rule2.setThreshold(60);
		// ruleItems.add(rule1);
		// ruleItems.add(rule2);
		//
		// dto.setRuleItems(ruleItems);
		System.out.println(JSON.toJSONString(dto));

	}
}
