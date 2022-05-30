package com.oracle.oBootMybatis01.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.oracle.oBootMybatis01.domain.Member;

@Repository
public class MemberJpaRepositoryImpl implements MemberJpaRepository {

	private final EntityManager em;
	
	public MemberJpaRepositoryImpl(EntityManager em) {
		this.em = em;
	}


	@Override
	public List<Member> findAll() {
		List<Member> memberList = em.createQuery("select m from Member m", Member.class).getResultList();
		return memberList;
	}

}
