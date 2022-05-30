package com.oracle.oBootMybatis01.dao;

import java.util.List;

import com.oracle.oBootMybatis01.domain.Member;

public interface MemberJpaRepository {
	List<Member> findAll();
}
