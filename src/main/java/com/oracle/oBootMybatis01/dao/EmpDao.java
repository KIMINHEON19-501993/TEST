package com.oracle.oBootMybatis01.dao;

import java.util.List;

import com.oracle.oBootMybatis01.model.Emp;
import com.oracle.oBootMybatis01.model.EmpDept;

public interface EmpDao {
	int total();

	List<Emp> listEmp(Emp emp);

	Emp detail(int empno);

	int update(Emp emp);

	List<Emp> listManager();

	int insert(Emp emp);

	int delete(int empno);

	List<Emp> listEmpKeyword(Emp emp);

	int totalKeyword(Emp emp);

	List<EmpDept> listEmpDept();

	List<EmpDept> listEmp(EmpDept empDept);

	String deptName(int deptno);
}
