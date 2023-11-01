package edu.pnu.domain;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class Board {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long seq;
	
	private String title; 
	
	@Column(updatable=false)
	private String writer;
	
	private String content;
	
	//@Column(insertable=false, updatable=false, columnDefinition="date default curdate()")
	@Column(insertable=false, updatable=false)
	private Date createDate;
	
	//@Column(insertable=false, updatable=false, columnDefinition="number default 0")
	@Column(insertable=false, updatable=false)
	private Long cnt;
}
