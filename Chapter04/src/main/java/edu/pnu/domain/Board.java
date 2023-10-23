package edu.pnu.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entity implementation class for Entity: Board
 *
 */
@Entity // 데이터베이스와 연결되는 엔티티 클래스라고 알려주는 어노테이션
@Table(name="BOARD") // 해당 테이블이랑 클래스 이름 같을 때는 작성안해도 상관없음
public class Board {
	@Id // primary key 
	@GeneratedValue(strategy= GenerationType.IDENTITY) // 자동으로 값 생성 (auto_increment)
	private Long seq;
	private String title;
	private String writer;
	private String content;
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;
	private Long cnt;
	
	@Override
	public String toString() {
		return "Board [seq=" + seq + ", title=" + title + ", writer=" + writer + ", content=" + content
				+ ", createDate=" + createDate + ", cnt=" + cnt + "]";
	}
	public Long getSeq() {
		return seq;
	}
	public void setSeq(Long seq) {
		this.seq = seq;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Long getCnt() {
		return cnt;
	}
	public void setCnt(Long cnt) {
		this.cnt = cnt;
	}
	
	
}
