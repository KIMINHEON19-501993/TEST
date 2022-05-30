package com.oracle.oBootMybatis01.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oracle.oBootMybatis01.model.Dept;
import com.oracle.oBootMybatis01.model.Emp;
import com.oracle.oBootMybatis01.model.SampleVO;
import com.oracle.oBootMybatis01.service.EmpService;

//@Controller + @ResponseBody
@RestController
public class EmpRestController {
	@Autowired
	private EmpService es;
	
	@RequestMapping("/helloText")
	public String helloText() {
		System.out.println("EmpRestController start...");
		String hello = "안녕";
		return hello;
	}
	
	@RequestMapping("/sample/sendVO2")
	public SampleVO sendVO2(int deptno) {
		System.out.println("@RestController deptno->"+deptno);
		SampleVO vo = new SampleVO();
		vo.setFirstName("길동");
		vo.setLastName("홍");
		vo.setMno(deptno);
		return vo;
	}
	
	@RequestMapping("/sendVO3")
	public List<Dept> sendVO3(){
		System.out.println("@RestController sendVO3 start...");
		List<Dept> deptList = es.deptSelect();
		return deptList;
	}
	
	@RequestMapping("/sample/sendVO1")
	public Dept sendVO1(int deptno) {
		System.out.println("@RestController sendVO1 deptno->"+deptno);
		Dept dept = es.detail2(deptno);
		System.out.println("@RestController sendVO1 dept.getDname->"+dept.getDname());
		return dept;
	}
	
	@RequestMapping("/empnoDelete")
	public String empnoDelete(Emp emp) {
		System.out.println("@RestController empnoDelete Start");
		System.out.println("@RestController empnoDelete emp.getEname()->"+emp.getEname());
		int delStatus = es.delete(emp.getEmpno());
		String delStatusStr = Integer.toString(delStatus);
		return delStatusStr;
	}
	
}
