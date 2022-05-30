package com.oracle.oBootMybatis01.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.oracle.oBootMybatis01.model.Emp;
import com.oracle.oBootMybatis01.model.EmpDept;

@Repository
public class EmpDaoImpl implements EmpDao {
	
	@Autowired
	private SqlSession session;
	
	@Override
	public int total() {
		int tot=0;
		System.out.println("EmpDaoImpl Start total...");
		try {
			// Naming Rule  --> Map ID
			tot=session.selectOne("tkEmpTotal"); // selectOne -> 하나의 row라는 전제, selectList -> 여러개의 row
			System.out.println("EmpDaoImpl total tot->"+tot);
		} catch (Exception e) {
			System.out.println("EmpDaoImpl total Exception->"+e.getMessage());
			
		}
		return tot;
	}

	@Override
	public List<Emp> listEmp(Emp emp) {
		List<Emp> empList = null;
		System.out.println("EmpDaoImpl listEmp Start...");
		try {
			// Naming Rule				  Map ID		 parameter
//			empList = session.selectList("tkEmpListAll3", emp);
			empList = session.selectList("tkEmpListAll", emp);
			System.out.println("EmpDaoImpl listEmp empList.size()-->"+empList.size());
			for(Emp rtnEmp : empList) {
				System.out.println("EmpDaoImpl listEmp empList.getEname()-->"+rtnEmp.getEname());
				System.out.println("EmpDaoImpl listEmp empList.getSal()-->"+rtnEmp.getSal());
			}
		} catch (Exception e) {
			System.out.println("EmpDaoImpl listEmp Exception->"+e.getMessage());
		}
		return empList;
	}

	@Override
	public Emp detail(int empno) {
		System.out.println("EmpDaoImpl detail start...");
		Emp emp = null;
		try {
			//                        mapper ID,    Parameter
			emp = session.selectOne("tkEmpSelOne", empno);
			System.out.println("EmpDaoImpl detail getEname->"+emp.getEname());
		} catch (Exception e) {
			System.out.println("EmpDaoImpl detail Exception->"+e.getMessage());
		}
		return emp;
	}

	@Override
	public int update(Emp emp) {
		System.out.println("EmpDaoImpl update start...");
		int kkk = 0;
		try {
			kkk = session.update("tkEmpUpdate", emp);
			System.out.println("$$$$$$$$$$$"+kkk);
		} catch (Exception e) {
			System.out.println("EmpDaoImpl update Exception->"+e.getMessage());
		}
		return kkk;
	}

	@Override
	public List<Emp> listManager() {
		List<Emp> empList = null;
		try {
			//  emp  관리자만 Select           Naming Rule
			empList = session.selectList("tkSelectManager");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return empList;
	}

	@Override
	public int insert(Emp emp) {
		int result = 0;
		System.out.println("EmpDaoImpl insert start...");
		try {
			result = session.insert("insertEmp", emp);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return result;
	}

	@Override
	public int delete(int empno) {
		int result = 0;
		try {
			result = session.delete("delete", empno);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return result;
	}

	@Override
	public List<Emp> listEmpKeyword(Emp emp) {
		List<Emp> listEmpKeyword = null;
		System.out.println("EmpDaoImpl listEmpKeyword start...");
		System.out.println("EmpDaoImpl listEmpKeyword emp.getSearch()->"+emp.getSearch());
		System.out.println("EmpDaoImpl listEmpKeyword emp.getKeyword()->"+emp.getKeyword());
		// 값이 null이면 입력값이 null로 바뀔수 있기 때문에 %로 변환
		if(emp.getKeyword()==null) emp.setKeyword("%");
		try {
			//keyword 검색
			//Naming Rule
			listEmpKeyword = session.selectList("tkEmpListKeyword", emp);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return listEmpKeyword;
	}

	@Override
	public int totalKeyword(Emp emp) {
		int tot=0;
		System.out.println("EmpDaoImpl start total...");
		if(emp.getKeyword()==null) emp.setKeyword("%");
		try {
			//Naming Rule ---> Map ID
			tot = session.selectOne("tkEmpTotalKeyword", emp);
			System.out.println("EmpDaoImpl totalKeyword tot->"+tot);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return tot;
	}

	@Override
	public List<EmpDept> listEmpDept() {
		System.out.println("EmpDaoImpl listEmpDept start...");
		List<EmpDept> empDept = null;
		try {
			empDept = session.selectList("TKlistEmpDept");
			System.out.println("EmpDaoImpl listEmpDept empDept.size()->"+empDept.size());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return empDept;
	}

	@Override
	public List<EmpDept> listEmp(EmpDept empDept) {
		System.out.println("EmpDaoImpl listEmp start...");
		List<EmpDept> empDept3 = null;
		try {
			empDept3 = session.selectList("TKlistEmpDept", empDept);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return empDept3;
	}

	@Override
	public String deptName(int deptno) {
		System.out.println("EmpDaoImpl deptName start...");
		String resultStr="";
		try {
			resultStr = session.selectOne("tkDeptName", deptno);
			System.out.println("EmpDaoImpl delete resultStr->"+resultStr);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return resultStr;
	}


}
