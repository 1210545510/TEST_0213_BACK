package com.aml;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.aml.api.dto.DicDTO;
import com.aml.api.dto.DicSubDTO;

public class TestDict {

	public static void main ( String[] args ) {
		 insertDic();
		// updateDic();
//		insertRule();
	}

	public static void insertDic () {
		DicDTO dto = new DicDTO();
		dto.setDicCode("d_work_ability");
		dto.setDicName("Allocation of work ability");
		dto.setRemark("设置审批分配规则(按工作能力)");
		System.out.println(JSON.toJSONString(dto));
	}

	public static void insertRule () {
		DicDTO dto = new DicDTO();
		dto.setDicCode("d_Analyst_assignment_rule");
		dto.setDicName("Analyst assignment rule setting");
		dto.setRemark("设置Analyst审批规则(1、平均分配;2、工作天数;3、工作能力)");

		List<DicSubDTO> dicSubs = new ArrayList<DicSubDTO>();
		DicSubDTO sub1 = new DicSubDTO();
		sub1.setSortIndex(1);
		sub1.setDicKey("1");
		sub1.setDicValue("Average allocation method");
		DicSubDTO sub2 = new DicSubDTO();
		sub2.setSortIndex(2);
		sub2.setDicKey("2");
		sub2.setDicValue("Sampling allocation of work experience");
		DicSubDTO sub3 = new DicSubDTO();
		sub3.setSortIndex(3);
		sub3.setDicKey("3");
		sub3.setDicValue("Allocation of work ability");
		dicSubs.add(sub1);
		dicSubs.add(sub2);
		dicSubs.add(sub3);

		dto.setDicSubs(dicSubs);
		System.out.println(JSON.toJSONString(dto));

	}

	public static void updateDic () {
		DicDTO dto = new DicDTO();
		dto.setDicCode("d_alert_list");
		dto.setDicName("Alert List");
		dto.setRemark("列表需要动态设置显示列时，需要添加到字典中, 如：Alert List; 所有列名添加字典项中, 如：score");

		List<DicSubDTO> dicSubs = new ArrayList<DicSubDTO>();
		DicSubDTO sub1 = new DicSubDTO();
		sub1.setSortIndex(1);
		sub1.setDicKey("1");
		sub1.setDicValue("Score");
		DicSubDTO sub2 = new DicSubDTO();
		sub2.setSortIndex(2);
		sub2.setDicKey("2");
		sub2.setDicValue("Subject");
		DicSubDTO sub3 = new DicSubDTO();
		sub3.setSortIndex(3);
		sub3.setDicKey("3");
		sub3.setDicValue("Amount");
		DicSubDTO sub4 = new DicSubDTO();
		sub4.setSortIndex(4);
		sub4.setDicKey("4");
		sub4.setDicValue("Volume");
		DicSubDTO sub5 = new DicSubDTO();
		sub5.setSortIndex(5);
		sub5.setDicKey("5");
		sub5.setDicValue("Geography");
		DicSubDTO sub6 = new DicSubDTO();
		sub6.setSortIndex(6);
		sub6.setDicKey("6");
		sub6.setDicValue("Scenario");
		DicSubDTO sub7 = new DicSubDTO();
		sub7.setSortIndex(7);
		sub7.setDicKey("7");
		sub7.setDicValue("Create Date");
		DicSubDTO sub8 = new DicSubDTO();
		sub8.setSortIndex(8);
		sub8.setDicKey("8");
		sub8.setDicValue("Due Date");
		DicSubDTO sub9 = new DicSubDTO();
		sub9.setSortIndex(9);
		sub9.setDicKey("9");
		sub9.setDicValue("Status");
		DicSubDTO sub10 = new DicSubDTO();
		sub10.setSortIndex(10);
		sub10.setDicKey("10");
		sub10.setDicValue("Focus");
		DicSubDTO sub11 = new DicSubDTO();
		sub11.setSortIndex(11);
		sub11.setDicKey("11");
		sub11.setDicValue("Focus type");
		DicSubDTO sub12 = new DicSubDTO();
		sub12.setSortIndex(12);
		sub12.setDicKey("12");
		sub12.setDicValue("Analyst");
		DicSubDTO sub13 = new DicSubDTO();
		sub13.setSortIndex(13);
		sub13.setDicKey("13");
		sub13.setDicValue("Highlights");
		DicSubDTO sub14 = new DicSubDTO();
		sub14.setSortIndex(14);
		sub14.setDicKey("14");
		sub14.setDicValue("Linked case");
		DicSubDTO sub15 = new DicSubDTO();
		sub15.setSortIndex(15);
		sub15.setDicKey("15");
		sub15.setDicValue("Business date");
		DicSubDTO sub16 = new DicSubDTO();
		sub16.setSortIndex(16);
		sub16.setDicKey("16");
		sub16.setDicValue("Account");
		DicSubDTO sub17 = new DicSubDTO();
		sub17.setSortIndex(17);
		sub17.setDicKey("17");
		sub17.setDicValue("Profile");
		DicSubDTO sub18 = new DicSubDTO();
		sub18.setSortIndex(18);
		sub18.setDicKey("18");
		sub18.setDicValue("Rule code");

		dicSubs.add(sub1);
		dicSubs.add(sub2);
		dicSubs.add(sub3);
		dicSubs.add(sub4);
		dicSubs.add(sub5);
		dicSubs.add(sub6);
		dicSubs.add(sub7);
		dicSubs.add(sub8);
		dicSubs.add(sub9);
		dicSubs.add(sub10);
		dicSubs.add(sub11);
		dicSubs.add(sub12);
		dicSubs.add(sub13);
		dicSubs.add(sub14);
		dicSubs.add(sub15);
		dicSubs.add(sub16);
		dicSubs.add(sub17);
		dicSubs.add(sub18);

		dto.setDicSubs(dicSubs);
		System.out.println(JSON.toJSONString(dto));

	}

	public static void deleteDic () {

	}

}
