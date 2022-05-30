package com.oracle.oBootMybatis01.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmpDept {
	// Emp 용
	private int empno;
	private String ename;
	private String job;
	private int mgr;
	private String hiredate;
	private int sal;
	private int comm;
	private int deptno;
	// Dept 용(많다는 가정)
	private String dname;
	private String loc;
}
