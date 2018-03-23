package com.cafe24.mysite.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.mysite.vo.BoardVo;
import com.cafe24.mysite.vo.Pager;

@Repository
public class BoardDao {

	@Autowired
	private SqlSession sqlSession;

	public int getTotalCount() {

		return sqlSession.selectOne("board.getTotalCountKeyword");
	}

	public int getTotalCount(String kwd) {

		return sqlSession.selectOne("board.getTotalCountKeyword", kwd);
	}

	public List<BoardVo> getListPage(Pager pager) {

		return sqlSession.selectList("board.getListPage", pager);
	}

	public List<BoardVo> getListSearch(String kwd, int startPage, int pageNum) {

		HashMap<String, Object> map = new HashMap<>();

		Pager pager = new Pager();
		pager.setPageStart(startPage);
		pager.setPageNum(pageNum);

		map.put("kwd", kwd);
		map.put("pager", pager);

		return sqlSession.selectList("board.getListSearch", map);
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
