package com.oracle.oBootMybatis01.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.oBootMybatis01.dao.DeptDao;
import com.oracle.oBootMybatis01.dao.EmpDao;
import com.oracle.oBootMybatis01.dao.Member1Dao;
import com.oracle.oBootMybatis01.model.Dept;
import com.oracle.oBootMybatis01.model.DeptVO;
import com.oracle.oBootMybatis01.model.Emp;
import com.oracle.oBootMybatis01.model.EmpDept;
import com.oracle.oBootMybatis01.model.Member1;

@Service
public class EmpServiceImpl implements EmpService {
	@Autowired
	private EmpDao ed;
	@Autowired
	private DeptDao dd;
	@Autowired
	private Member1Dao md;
	
	@Override
	public int total() {
		System.out.println("EmpServiceImpl Start total..");
		int totCnt = ed.total();
		System.out.println("EmpServiceImpl total totCnt->"+totCnt);
		return totCnt;
	}

	@Override
	public List<Emp> listEmp(Emp emp) {
		List<Emp> empList = null;
		System.out.println("EmpServiceImpl listEmp Start...");
		empList = ed.listEmp(emp);
		System.out.println("EmpServiceImpl listEmp empList.size()->"+empList.size());
		return empList;
	}

	@Override
	public Emp detail(int empno) {
		System.out.println("EmpServiceImpl detail...");
		Emp emp = null;
		emp = ed.detail(empno);
		return emp;
	}

	@Override
	public int update(Emp emp) {
		// Business 업무
		System.out.println("EmpServiceImpl update...");
		int kkk = 0;
		kkk = ed.update(emp);
		System.out.println("@@@@@"+kkk);
		return kkk;
	}

	@Override
	public List<Emp> listManager() {
		List<Emp> empList = null;
		empList = ed.listManager();
		return empList;
	}

	@Override
	public List<Dept> deptSelect() {
		List<Dept> deptList = null;
		System.out.println("EmpServiceImpl deptSelect start...");
		deptList = dd.deptSelect();
		System.out.println("EmpServiceImpl deptSelect deptList.size()->"+deptList.size());
		return deptList;
	}
	
	// ed.insert(emp)
	@Override
	public int insert(Emp emp) {
		int result = 0;
		System.out.println("EmpServiceImpl insert start...");
		result = ed.insert(emp);
		return result;
	}

	@Override
	public int delete(int empno) {
		int result = 0;
		result = ed.delete(empno);
		return result;
	}

	@Override
	public List<Emp> listEmpKeyword(Emp emp) {
		List<Emp> listEmpKeyword = null;
		System.out.println("EmpServiceImpl listEmpKeyword start...");
		listEmpKeyword = ed.listEmpKeyword(emp);
		System.out.println("EmpServiceImpl listEmpKeyword listEmpKeyword.size()->"+listEmpKeyword.size());
		return listEmpKeyword;
	}

	@Override
	public int totalKeyword(Emp emp) {
		System.out.println("EmpServiceImpl totalKeyword Start...");
		int totCnt = ed.totalKeyword(emp);
		System.out.println("EmpServiceImpl totalKeyword totCnt->"+totCnt);
		return totCnt;
	}

	@Override
	public List<EmpDept> listEmpDept() {
		List<EmpDept> empDeptList = null;
		System.out.println("EmpServiceImpl listEmpDept start...");
		empDeptList = ed.listEmpDept();
		System.out.println("EmpServiceImpl listEmpDept empDeptList.size()->"+empDeptList.size());
		return empDeptList;
	}

	@Override
	public void insertDept(DeptVO deptVO) {
		System.out.println("EmpServiceImpl insertDept start...");
		dd.insertDept(deptVO);
	}

	@Override
	public void selListDept(HashMap<String, Object> map) {
		System.out.println("EmpServiceImpl selListDept start...");
		dd.selListDept(map);
	}

	@Override
	public List<EmpDept> listEmp(EmpDept empDept) {
		System.out.println("EmpServiceImpl listEmp start...");
		return ed.listEmp(empDept);
	}

	@Override
	public String deptName(int deptno) {
		System.out.println("EmpServiceImpl deptName start...");
		return ed.deptName(deptno);
	}

	@Override
	public Dept detail2(int deptno) {
		System.out.println("EmpServiceImpl detail2 start...");
		Dept dept = dd.detail(deptno);
		return dept;
	}

	@Override
	public int memCount(String id) {
		System.out.println("EmpServiceImpl memCount id ->"+id);
		return md.memCount(id);
	}

	@Override
	public List<Member1> listMem(Member1 member1) {
		System.out.println("EmpService listMem Start..");
		return md.listMem(member1);
	}
}
