package edu.pnu.board.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.pnu.board.domain.Member;

public interface MemberRepository extends JpaRepository<Member, String> {

}
