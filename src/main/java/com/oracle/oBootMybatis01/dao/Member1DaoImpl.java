package com.oracle.oBootMybatis01.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.oracle.oBootMybatis01.model.Member1;

@Repository
public class Member1DaoImpl implements Member1Dao {
	
	@Autowired
	private SqlSession session;
	
	@Override
	public int memCount(String id) {
		int result = 0;
		System.out.println("Member1DaoImpl id->"+id);
		try {
			result = session.selectOne("memCount", id);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return result;
	}

	@Override
	public List<Member1> listMem(Member1 member1) {
		System.out.println("Member1DaoImpl listMem start...");
		List<Member1> listMember1 = null;
		try {
			listMember1 = session.selectList("listMember1", member1);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return listMember1;
	}

}
