package com.cafe24.mysite.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.mvc.util.AutoClose;
import com.cafe24.mvc.util.ConnectionFactroy;
import com.cafe24.mysite.vo.BoardVo;
import com.cafe24.mysite.vo.Pager;
import com.cafe24.mysite.vo.UserVo;

@Repository
public class BoardDao {

	@Autowired
	private SqlSession sqlSession;

	public int getTotalCount() {

		return sqlSession.selectOne("board.getTotalCount");
	}

	public int getTotalCount(String kwd) {
		String kd = "%" + kwd + "%";

		return sqlSession.selectOne("board.getTotalCountKeyword", kd);
	}

	public List<BoardVo> getListPage(Pager pager) {

		return sqlSession.selectList("board.getListPage", pager);
	}

	public List<BoardVo> getListSearch(String kwd, int startPage, int pageNum) {
		Pager pager = new Pager();
		
		pager.setPageStart(startPage);
		pager.setPageNum(pageNum);
		pager.setKd("%" + kwd + "%");
		
		return sqlSession.selectList("board.getListSearch",pager);
	}

	public int groupNoSearch() {
		int count = sqlSession.selectOne("board.groupNoSearch");

		return count + 1;

	}

	public boolean insert(BoardVo vo) {

		vo.setGroupNo((long) groupNoSearch());
		vo.setOrderNo((long) 1);
		vo.setDepth((long) 0);
		vo.setCount((long) 0);
		int count = sqlSession.insert("board.insertBoard", vo);
		return count == 1;
	}

	public boolean replyWrite(BoardVo vo) {
		int count = sqlSession.insert("board.replyInsert", vo);

		return count == 1;
	}

	public boolean delete(Long no) {
		int count = sqlSession.delete("board.delete", no);

		return count == 1;

	}

	public boolean groupDelete(Long groupNo, Long orderNo) {

		BoardVo vo = new BoardVo();
		vo.setGroupNo(groupNo);
		vo.setOrderNo(orderNo);
		int count = sqlSession.delete("board.groupDelete", vo);

		return count == 1;

	}

	public BoardVo getBoard(Long no) {

		
		return sqlSession.selectOne("board.getBoard",no);
	}

	public boolean updateOrder(Long groupNo, Long orderNo) {
		BoardVo vo = new BoardVo();
		vo.setGroupNo(groupNo);
		vo.setOrderNo(orderNo);
		
		int count = sqlSession.update("board.updateOrder",vo);
		

		return count == 1;

	}

	public boolean update(BoardVo vo) {
		int count = sqlSession.update("board.update",vo);
		
		return count == 1;

	}

}
