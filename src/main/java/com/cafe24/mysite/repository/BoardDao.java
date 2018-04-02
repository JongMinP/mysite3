package com.cafe24.mysite.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.mysite.vo.BoardVo;
import com.cafe24.pager.Pager;

@Repository
public class BoardDao {

	@Autowired
	private SqlSession sqlSession;

	public int getTotalCount(String kwd) {

		return sqlSession.selectOne("board.getTotalCountKeyword", kwd);
	}

	public List<BoardVo> getListPage(Pager pager, String kwd) {

		HashMap<String, Object> map = new HashMap<>();

		map.put("pager", pager);
		map.put("kwd", kwd);

		return sqlSession.selectList("board.getListPage", map);
	}

	public int groupNoSearch() {

		int count = sqlSession.selectOne("board.groupNoSearch");

		return count + 1;

	}

	public boolean insert(BoardVo vo) {

		if (vo.getGroupNo() == null)
			vo.setGroupNo((long) groupNoSearch());

		int count = sqlSession.insert("board.insertBoard", vo);

		return count == 1;
	}

	public boolean delete(Long no) {
		int count = sqlSession.delete("board.delete", no);

		return count == 1;

	}

	public BoardVo getBoard(Long no) {

		return sqlSession.selectOne("board.getBoard", no);
	}

	public boolean updateOrder(Long groupNo, Long orderNo) {

		Map<String, Object> map = new HashMap<>();

		map.put("groupNo", groupNo);
		map.put("orderNo", orderNo);

		int count = sqlSession.update("board.updateOrder", map);

		return count == 1;

	}

	public boolean update(BoardVo vo) {
		int count = sqlSession.update("board.update", vo);

		return count == 1;

	}

}
