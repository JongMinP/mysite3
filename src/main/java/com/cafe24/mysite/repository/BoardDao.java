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
import com.cafe24.mysite.vo.BoardVo;
import com.cafe24.mysite.vo.UserVo;

@Repository
public class BoardDao {

	public int getTotalCount() {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuilder sql = new StringBuilder();

		try {
			conn = ConnectionFactroy.getInstance().createConnection();

			sql.append("select count(*) ");
			sql.append("from board ");
			pstmt = conn.prepareStatement(sql.toString());

			rs = pstmt.executeQuery();

			if (rs.next()) {
				result = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			AutoClose.closeResource(rs, pstmt, conn);
		}

		return result;
	}
	
	public int getTotalCount(String kwd) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuilder sql = new StringBuilder();

		try {
			conn = ConnectionFactroy.getInstance().createConnection();

			sql.append("select count(*) ");
			sql.append("from board ");
			sql.append("where title LIKE ? or content LIKE ?  ");
			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setString(1, "%" + kwd + "%");
			pstmt.setString(2, "%" + kwd + "%");
			
			rs = pstmt.executeQuery();

			if (rs.next()) {
				result = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			AutoClose.closeResource(rs, pstmt, conn);
		}

		return result;
	}
	

	public List<BoardVo> getList() {
		List<BoardVo> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuilder sql = new StringBuilder();

		try {
			conn = ConnectionFactroy.getInstance().createConnection();

			sql.append("select b.no,title,content,group_no,order_no,depth,count,file, ");
			sql.append("date_format(b.reg_date,'%Y-%m-%d') as reg_date , user_no, u.name ");
			sql.append("from board b , users u ");
			sql.append("where b.user_no = u.no ");
			sql.append("order by b.group_no desc , b.order_no asc ");
			pstmt = conn.prepareStatement(sql.toString());

			rs = pstmt.executeQuery();

			while (rs.next()) {
				BoardVo vo = new BoardVo();
				vo.setNo(rs.getLong(1));
				vo.setTitle(rs.getString(2));
				vo.setContent(rs.getString(3));
				vo.setGroupNo(rs.getLong(4));
				vo.setOrderNo(rs.getLong(5));
				vo.setDepth(rs.getLong(6));
				vo.setCount(rs.getLong(7));
				vo.setFile(rs.getString(8));
				vo.setRegDate(rs.getString(9));
				vo.setUser(new UserVo(rs.getLong(10), rs.getString(11)));

				list.add(vo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			AutoClose.closeResource(rs, pstmt, conn);
		}

		return list;
	}

	public List<BoardVo> getListPage(int startPage, int pageNum) {
		List<BoardVo> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuilder sql = new StringBuilder();

		try {
			conn = ConnectionFactroy.getInstance().createConnection();

			sql.append("select b.no,title,content,group_no,order_no,depth,count,file, ");
			sql.append("date_format(b.reg_date,'%Y-%m-%d') as reg_date , user_no, u.name ");
			sql.append("from board b , users u ");
			sql.append("where b.user_no = u.no ");
			sql.append("order by b.group_no desc , b.order_no asc ");
			sql.append("LIMIT ? , ? ");

			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setInt(1, startPage);
			pstmt.setInt(2, pageNum);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				BoardVo vo = new BoardVo();
				vo.setNo(rs.getLong(1));
				vo.setTitle(rs.getString(2));
				vo.setContent(rs.getString(3));
				vo.setGroupNo(rs.getLong(4));
				vo.setOrderNo(rs.getLong(5));
				vo.setDepth(rs.getLong(6));
				vo.setCount(rs.getLong(7));
				vo.setFile(rs.getString(8));
				vo.setRegDate(rs.getString(9));
				vo.setUser(new UserVo(rs.getLong(10), rs.getString(11)));

				list.add(vo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			AutoClose.closeResource(rs, pstmt, conn);
		}

		return list;
	}

	public List<BoardVo> getListSearch(String kwd, int startPage,int pageNum) {
		List<BoardVo> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuilder sql = new StringBuilder();

		try {
			conn = ConnectionFactroy.getInstance().createConnection();

			sql.append("select b.no,title,content,group_no,order_no,depth,count,file, ");
			sql.append("date_format(b.reg_date,'%Y-%m-%d') as reg_date , user_no, u.name ");
			sql.append("from board b , users u ");
			sql.append("where b.user_no = u.no and (b.title LIKE ?  or b.content LIKE   ?) ");
			sql.append("order by b.group_no desc , b.order_no asc ");
			sql.append("LIMIT ? , ? ");
			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setString(1, "%" + kwd + "%");
			pstmt.setString(2, "%" + kwd + "%");
			pstmt.setInt(3, startPage);
			pstmt.setInt(4, pageNum);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				BoardVo vo = new BoardVo();
				vo.setNo(rs.getLong(1));
				vo.setTitle(rs.getString(2));
				vo.setContent(rs.getString(3));
				vo.setGroupNo(rs.getLong(4));
				vo.setOrderNo(rs.getLong(5));
				vo.setDepth(rs.getLong(6));
				vo.setCount(rs.getLong(7));
				vo.setFile(rs.getString(8));
				vo.setRegDate(rs.getString(9));
				vo.setUser(new UserVo(rs.getLong(10), rs.getString(11)));

				list.add(vo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			AutoClose.closeResource(rs, pstmt, conn);
		}

		return list;
	}

	public int groupNoSearch() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int i = -1;

		StringBuilder sql = new StringBuilder();

		try {
			conn = ConnectionFactroy.getInstance().createConnection();
			sql.append("select ifnull(max(group_no), 0) as group_no ");
			sql.append("from board ");

			pstmt = conn.prepareStatement(sql.toString());

			rs = pstmt.executeQuery();

			if (rs.next())
				i = rs.getInt(1);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			AutoClose.closeResource(rs, pstmt, conn);
		}

		return i + 1;

	}

	public boolean insert(BoardVo vo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		StringBuilder sql = new StringBuilder();
		int groupNo = groupNoSearch();

		try {
			conn = ConnectionFactroy.getInstance().createConnection();

			sql.append("insert into board ");
			sql.append("values(null,?,?,?,?,?,0,?, now(), ?) ");

			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setLong(3, groupNo);
			pstmt.setLong(4, 1L);
			pstmt.setLong(5, 0L);
			pstmt.setString(6, vo.getFile());
			pstmt.setLong(7, vo.getUser().getNo());

			int count = pstmt.executeUpdate();

			result = (count == 1);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			AutoClose.closeResource(pstmt, conn);
		}

		return result;
	}

	public boolean replyWrite(BoardVo vo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		StringBuilder sql = new StringBuilder();

		try {
			conn = ConnectionFactroy.getInstance().createConnection();

			sql.append("insert into board ");
			sql.append("values(null,?,?,?,?,?,0,?, now(), ?) ");

			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setLong(3, vo.getGroupNo());
			pstmt.setLong(4, vo.getOrderNo());
			pstmt.setLong(5, vo.getDepth());
			pstmt.setString(6, vo.getFile());
			pstmt.setLong(7, vo.getUser().getNo());

			int count = pstmt.executeUpdate();

			result = (count == 1);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			AutoClose.closeResource(pstmt, conn);
		}

		return result;
	}

	public boolean delete(Long no) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		StringBuilder sql = new StringBuilder();

		try {
			conn = ConnectionFactroy.getInstance().createConnection();
			sql.append("delete from board ");
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
	
	
	public boolean groupDelete(Long groupNo, Long orderNo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		StringBuilder sql = new StringBuilder();

		try {
			conn = ConnectionFactroy.getInstance().createConnection();
			sql.append("delete from board ");
			sql.append("where group_no = ? and order_no > ? ");

			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setLong(1, groupNo);
			pstmt.setLong(2, orderNo);

			int count = pstmt.executeUpdate();

			result = (count == 1);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			AutoClose.closeResource(pstmt, conn);
		}

		return result;

	}

	public BoardVo getBoard(Long no) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardVo vo = new BoardVo();
		StringBuilder sql = new StringBuilder();

		try {
			conn = ConnectionFactroy.getInstance().createConnection();

			sql.append("select b.no,title,content,group_no,order_no,depth,count,file, ");
			sql.append("date_format(b.reg_date,'%Y-%m-%d') as reg_date , user_no, u.name ");
			sql.append("from board b , users u ");
			sql.append("where b.user_no = u.no and b.no = ? ");
			sql.append("order by b.group_no desc , b.order_no asc ");
			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setLong(1, no);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				vo.setNo(rs.getLong(1));
				vo.setTitle(rs.getString(2));
				vo.setContent(rs.getString(3));
				vo.setGroupNo(rs.getLong(4));
				vo.setOrderNo(rs.getLong(5));
				vo.setDepth(rs.getLong(6));
				vo.setCount(rs.getLong(7));
				vo.setFile(rs.getString(8));
				vo.setRegDate(rs.getString(9));
				vo.setUser(new UserVo(rs.getLong(10), rs.getString(11)));
				vo.setComments(new CommentDao().getList(rs.getLong(1)));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			AutoClose.closeResource(rs, pstmt, conn);
		}

		return vo;
	}

	public boolean updateOrder(Long groupNo, Long orderNo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		StringBuilder sql = new StringBuilder();

		try {
			conn = ConnectionFactroy.getInstance().createConnection();
			sql.append("update board ");
			sql.append("set order_no = order_no + 1 ");
			sql.append("where group_no = ? and order_no >= ? ");

			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setLong(1, groupNo);
			pstmt.setLong(2, orderNo);

			int count = pstmt.executeUpdate();

			result = (count == 1);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			AutoClose.closeResource(pstmt, conn);
		}

		return result;

	}

	public boolean update(BoardVo vo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		StringBuilder sql = new StringBuilder();

		try {
			conn = ConnectionFactroy.getInstance().createConnection();
			sql.append("update board ");
			sql.append("set count = ?,title = ?, content = ?  ");
			sql.append("where no = ? ");

			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setLong(1, vo.getCount());
			pstmt.setString(2, vo.getTitle());
			pstmt.setString(3, vo.getContent());
			pstmt.setLong(4, vo.getNo());
			// 미완성

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
