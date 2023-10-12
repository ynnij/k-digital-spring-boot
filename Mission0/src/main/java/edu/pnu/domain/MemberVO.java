package edu.pnu.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor // 모든 필드로 생성자
@NoArgsConstructor // 기본 생성자
public class MemberVO {
	private int id;
	private String pass;
	private String name;
	private Date regidate;
	
}
