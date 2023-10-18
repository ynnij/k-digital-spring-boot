package edu.pnu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.pnu.domain.BoardVO;

public class BoardDAO {
	Connection con;
	public BoardDAO() {
		try {
			Class.forName("org.h2.Driver");
			con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/.h2/mission2", "sa", "abcd");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public int addBoard(BoardVO boardVO) {
		if(boardVO.getTitle()==null ||  boardVO.getWriter()==null ||boardVO.getContent()==null)
			return 0;
		try {
			String sql = "insert into board(title, writer,content, cnt) "
					+ "values (?,?,?,?) ";
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setString(1, boardVO.getTitle());
			psmt.setString(2, boardVO.getWriter());
			psmt.setString(3, boardVO.getContent());
			psmt.setInt(4, 0);
			
			return psmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public List<BoardVO> getBoards(){
		List<BoardVO> list = new ArrayList<>();
		try {
			String sql = "select * from board";
			Statement st = con.createStatement();
			
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				list.add(BoardVO.builder()
						.seq(rs.getInt("seq"))
						.title(rs.getString("title"))
						.writer(rs.getString("writer"))
						.content(rs.getString("content"))
						.createDate(rs.getDate("createdate"))
						.cnt(rs.getInt("cnt")).build());
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public BoardVO getBoard(int seq) {
		BoardVO board = new BoardVO();
		try {
			String sql = "select * from board where seq=?";
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setInt(1,seq);
			
			ResultSet rs = psmt.executeQuery();
			
			while(rs.next()) {
				board.setSeq(rs.getInt("seq"));
				board.setTitle(rs.getString("title"));
				board.setWriter(rs.getString("writer"));
				board.setContent(rs.getString("content"));
				board.setCreateDate(rs.getDate("createdate"));
				board.setCnt(rs.getInt("cnt"));
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return board;
	}

	public int updateBoard(BoardVO board) {
		BoardVO b = getBoard(board.getSeq());
		
		String sql = "update board set ";
		if(board.getTitle()!=null) sql+= ("title ='"+board.getTitle()+"'");
		else					sql+= ("title ='"+b.getTitle()+"'");
		
		if(board.getWriter()!=null) sql+= (", writer='"+board.getWriter()+"'");
		else					sql+= (", writer='"+b.getWriter()+"'");
		
		if(board.getContent()!=null) sql+= (", content='"+board.getContent()+"'");
		else					sql+= (", content='"+b.getContent()+"'");
		
		sql += (" where seq = "+board.getSeq());
		
		try {
			Statement st = con.createStatement();
			return st.executeUpdate(sql);
		} catch(Exception e){
			e.printStackTrace();
		}
		
		
		return 0;
	}
	
	public int updateBoard2(BoardVO board) {
		String sql = null;
		if(board.getTitle()!=null) {
			if(sql == null) sql = "update board set ";
				
			sql += (" title ='"+board.getTitle()+"'");
		}
		if(board.getWriter()!=null) {
			if(sql == null) {
				sql = "update board set ";
				sql += (" writer='"+board.getWriter()+"'");
			}
			else 
				sql += (" ,writer='"+board.getWriter()+"'");
		}
		if(board.getContent()!=null) {
			if(sql == null) {
				sql = "update board set ";
				sql+=(" content='"+board.getContent()+"'");
			}
			else 
				sql+=(" ,content='"+board.getContent()+"'");
		}
		sql += (" where seq="+board.getSeq());
		
		try {
			Statement st = con.createStatement();
			
			return st.executeUpdate(sql);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	public int updateBoard1(BoardVO board) {
		try {
			String sql = "update board set ";
			if(board.getContent()!=null && board.getTitle()!=null && board.getWriter()!=null) {
				sql += " content ='"+board.getContent()+"', title='"+board.getTitle()+"', "
						+"writer ='"+board.getWriter()+"'";
			} else if(board.getContent()!=null && board.getTitle()!=null) {
				sql += " content ='"+board.getContent()+"', title='"+board.getTitle()+"'";
			} else if(board.getContent()!=null && board.getWriter()!=null) {
				sql += " content ='"+board.getContent()+"', "
						+"writer ='"+board.getWriter()+"'";
			} else if(board.getTitle()!=null && board.getWriter()!=null) {
				sql += " title='"+board.getTitle()+"', "
						+"writer ='"+board.getWriter()+"'";
			} else if(board.getContent()!=null)
				sql += " content='"+board.getContent()+"'";
			else if(board.getTitle()!=null)
				sql += " title='"+board.getTitle()+"'";
			else if(board.getWriter()!=null)
				sql += " writer='"+board.getWriter()+"'";
			sql +=" where seq="+board.getSeq();
			
			Statement st = con.createStatement();
			return st.executeUpdate(sql);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int removeBoard(int seq) {
		try {
			String sql = "delete board where seq=?";
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setInt(1, seq);
			
			return psmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
}
