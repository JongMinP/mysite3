package com.cafe24.mysite.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.mysite.vo.CommentVo;

@Repository
public class CommentDao {

	@Autowired
	private SqlSession sqlSession;

	public List<CommentVo> getList(Long boardNo) {

		return sqlSession.selectList("comment.getList", boardNo);
	}

	public boolean delete(Long no) {

		int count = sqlSession.delete("comment.commentDelete", no);

		return count == 1;

	}

	public boolean boardDelete(Long boardNo) {
		int count = sqlSession.delete("comment.boardDelete", boardNo);

		return count == 1;

	}

	public boolean insert(CommentVo vo) {
		int count = sqlSession.insert("comment.commentInsert", vo);

		return count == 1;
	}

}
