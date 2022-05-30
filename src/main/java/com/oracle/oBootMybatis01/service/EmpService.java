package com.oracle.oBootMybatis01.service;

import java.util.HashMap;
import java.util.List;

import com.oracle.oBootMybatis01.model.Dept;
import com.oracle.oBootMybatis01.model.DeptVO;
import com.oracle.oBootMybatis01.model.Emp;
import com.oracle.oBootMybatis01.model.EmpDept;
import com.oracle.oBootMybatis01.model.Member1;

public interface EmpService {
	int total();

	List<Emp> listEmp(Emp emp);

	Emp detail(int empno);

	int update(Emp emp);

	List<Emp> listManager();

	List<Dept> deptSelect();

	int insert(Emp emp);

	int delete(int empno);

	List<Emp> listEmpKeyword(Emp emp);

	int totalKeyword(Emp emp);

	List<EmpDept> listEmpDept();

	void insertDept(DeptVO deptVO);

	void selListDept(HashMap<String, Object> map);

	List<EmpDept> listEmp(EmpDept empDept);

	String deptName(int deptno);

	Dept detail2(int deptno);

	int memCount(String id);

	List<Member1> listMem(Member1 member1);
}
