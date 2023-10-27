package edu.pnu.domain;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Board {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long seq;
	private String title;
	private String content;
	@Temporal(value=TemporalType.TIMESTAMP)
	private Date createDate;
	private Long cnt;
	
	
	//디대일 매핑 추가
	@ManyToOne //내 클래스는 Many 연결되는 클래스는 One
	@JoinColumn(name="MEMBER_ID") //id 컬럼명 겹치는걸 방지하기 위해 클래스명_ID 로 많이 지정함
	private Member member; // Member 클래스 안에 있는 MEMBER_ID로 join하겠다는 의미
	
	public void setMember(Member member) {
		this.member = member;
		member.getBoardList().add(this);
	}
}
