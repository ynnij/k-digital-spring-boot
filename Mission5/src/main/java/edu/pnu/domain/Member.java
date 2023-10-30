package edu.pnu.domain;

import java.util.Date;

import org.springframework.boot.autoconfigure.web.WebProperties.Resources.Chain.Strategy;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
public class Member {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String pass;
	private String name;
	private Date regidate;
}
