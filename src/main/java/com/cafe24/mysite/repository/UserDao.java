package com.cafe24.mysite.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.cafe24.mvc.util.AutoClose;
import com.cafe24.mvc.util.ConnectionFactroy;
import com.cafe24.mysite.exception.UserDaoException;
import com.cafe24.mysite.vo.UserVo;

@Repository
public class UserDao {

	public boolean update(UserVo vo) throws UserDaoException {

		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		StringBuilder sql = new StringBuilder();
		try {
			conn = ConnectionFactroy.getInstance().createConnection();

			sql.append(" update users ");
			sql.append(" set name = ? , password = password(?), gender = ? ");
			sql.append(" where no = ? ");

			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getGender());
			pstmt.setLong(4, vo.getNo());

			int count = pstmt.executeUpdate();

			result = (count == 1);

		} catch (SQLException e) {
			throw new UserDaoException();
		} finally {

			AutoClose.closeResource(pstmt, conn);
		}

		return result;

	}

	public UserVo getNo(Long no) throws UserDaoException {
		UserVo result = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuilder sql = new StringBuilder();

		try {
			conn = ConnectionFactroy.getInstance().createConnection();

			sql.append(" select no , name , gender ");
			sql.append(" from users  ");
			sql.append(" where no = ? ");

			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setLong(1, no);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				result = new UserVo();
				result.setNo(rs.getLong(1));
				result.setName(rs.getString(2));
				result.setGender(rs.getString(3));
			}

		} catch (SQLException e) {
			throw new UserDaoException();
		} finally {

			AutoClose.closeResource(rs, pstmt, conn);
		}

		return result;
	}

	public UserVo get(String email, String password)  {
		UserVo result = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuilder sql = new StringBuilder();

		try {
			conn = ConnectionFactroy.getInstance().createConnection();

			sql.append(" select no , name ");
			sql.append(" from users  ");
			sql.append(" where email=? and password =password(?) ");

			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setString(1, email);
			pstmt.setString(2, password);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				result = new UserVo();
				result.setNo(rs.getLong(1));
				result.setName(rs.getString(2));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			AutoClose.closeResource(rs, pstmt, conn);
		}

		return result;
	}

	public boolean insert(UserVo vo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		StringBuilder sql = new StringBuilder();
		try {
			conn = ConnectionFactroy.getInstance().createConnection();

			sql.append(" insert ");
			sql.append(" into users ");
			sql.append(" values(null,?,?, password(?),?,now() ) ");

			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getEmail());
			pstmt.setString(3, vo.getPassword());
			pstmt.setString(4, vo.getGender());

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
