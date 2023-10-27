package edu.pnu.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude="boardList") // 순환 참조 방지를 위한 속성 추가 
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {
	@Id
	@Column(name="MEMBER_ID")
	private String id;
	private String password;
	private String name;
	private String role;
	
	@OneToMany(mappedBy="member", fetch=FetchType.EAGER, cascade=CascadeType.ALL) //종속성 나타내기 위한 mappedBy 
	private List<Board> boardList = new ArrayList<Board>(); // 1대 다 매핑이기때문에 리스트
}
