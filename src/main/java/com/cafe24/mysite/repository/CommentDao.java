package com.cafe24.mysite.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.cafe24.mvc.util.AutoClose;
import com.cafe24.mvc.util.ConnectionFactroy;
import com.cafe24.mysite.vo.CommentVo;

@Repository
public class CommentDao {

	public List<CommentVo> getList(Long boardNo) {
		List<CommentVo> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuilder sql = new StringBuilder();

		try {
			conn = ConnectionFactroy.getInstance().createConnection();

			sql.append("select no, content, date_format(reg_date,'%Y-%m-%d') as reg_date, ");
			sql.append(" user_no, board_no ");
			sql.append("from comment ");
			sql.append("where board_no = ? ");
			sql.append("order by no asc ");
			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setLong(1, boardNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				CommentVo vo = new CommentVo();
				vo.setNo(rs.getLong(1));
				vo.setContent(rs.getString(2));
				vo.setRegDate(rs.getString(3));
				vo.setUser(new UserDao().getNo(rs.getLong(4)));
				vo.setBoardNo(rs.getLong(5));

				list.add(vo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			AutoClose.closeResource(rs, pstmt, conn);
		}

		return list;
	}

	public CommentVo getComment(int no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CommentVo vo = new CommentVo();
		StringBuilder sql = new StringBuilder();

		try {
			conn = ConnectionFactroy.getInstance().createConnection();

			sql.append("select no, content, date_format(reg_date,'%Y-%m-%d') as reg_date, ");
			sql.append(" user_no, board_no ");
			sql.append("from comment ");
			sql.append("where no = ? ");

			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setInt(1, no);

			rs = pstmt.executeQuery();

			if (rs.next()) {

				vo.setNo(rs.getLong(1));
				vo.setContent(rs.getString(2));
				vo.setRegDate(rs.getString(3));
				vo.setUser(new UserDao().getNo(rs.getLong(4)));
				vo.setBoardNo(rs.getLong(5));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			AutoClose.closeResource(rs, pstmt, conn);
		}

		return vo;
	}

	public boolean delete(Long no) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		StringBuilder sql = new StringBuilder();

		try {
			conn = ConnectionFactroy.getInstance().createConnection();
			sql.append("delete from comment ");
			sql.append("where no = ? ");

			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setLong(1, no);

			int count = pstmt.executeUpdate();

			result = (count == 1);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			AutoClose.closeResource(pstmt, conn);
		}

		return result;

	}
	
	public boolean boardDelete(Long boardNo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		StringBuilder sql = new StringBuilder();

		try {
			conn = ConnectionFactroy.getInstance().createConnection();
			sql.append("delete from comment ");
			sql.append("where board_no = ? ");

			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setLong(1, boardNo);

			int count = pstmt.executeUpdate();

			result = (count == 1);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			AutoClose.closeResource(pstmt, conn);
		}

		return result;

	}

	public boolean insert(CommentVo vo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		StringBuilder sql = new StringBuilder();

		try {
			conn = ConnectionFactroy.getInstance().createConnection();
			sql.append("insert into comment ");
			sql.append("values(null,?,now(),?,? ) ");

			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setString(1, vo.getContent());
			pstmt.setLong(2, vo.getUser().getNo());
			pstmt.setLong(3, vo.getBoardNo());

			int count = pstmt.executeUpdate();

			result = (count == 1);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			AutoClose.closeResource(pstmt, conn);
		}

		return result;
	}
	
	
	public boolean update(CommentVo vo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		StringBuilder sql = new StringBuilder();

		try {
			conn = ConnectionFactroy.getInstance().createConnection();
			sql.append("update comment ");
			sql.append("set  content = ? ");
			sql.append("where no = ? ");

			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setString(1, vo.getContent());
			pstmt.setLong(2, vo.getNo());

			int count = pstmt.executeUpdate();

			result = (count == 1);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			AutoClose.closeResource(pstmt, conn);
		}

		return result;
	}

}
