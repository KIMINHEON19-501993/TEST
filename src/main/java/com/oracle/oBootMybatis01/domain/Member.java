package com.oracle.oBootMybatis01.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Member3")
public class Member {
	@Id
	private Long id;
	private String password;
	private String name;
	private Date reg_date;
}
