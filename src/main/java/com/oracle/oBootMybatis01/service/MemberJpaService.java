package com.oracle.oBootMybatis01.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oracle.oBootMybatis01.dao.MemberJpaRepository;
import com.oracle.oBootMybatis01.domain.Member;

@Transactional
@Service
public class MemberJpaService {
	private final MemberJpaRepository memberJpaRepository;

	public MemberJpaService(MemberJpaRepository memberJpaRepository) {
		this.memberJpaRepository = memberJpaRepository;
	}
	
	//전체회원 조회
	public List<Member> getListAllMember(){
		List<Member> listMember = memberJpaRepository.findAll();
		System.out.println("MemberJpaService getListAllMember listMember.size()->"+listMember.size());
		return listMember;
	}
	
}
