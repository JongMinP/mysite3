package com.cafe24.mysite.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.mysite.vo.UserVo;

@Repository
public class UserDao {

	@Autowired
	private SqlSession sqlSession;

	public boolean update(UserVo vo) {

		System.out.println(vo);
		int count = sqlSession.update("user.update", vo);

		return count == 1;

	}

	public UserVo getNo(Long no) {

		return sqlSession.selectOne("user.getByNo", no);
	}

	public UserVo get(UserVo vo) {

		return sqlSession.selectOne("user.getByEmailAndPassword", vo);
	}

	public boolean insert(UserVo vo) {
		int count = sqlSession.insert("user.insert", vo);

		return count == 1;
	}

}
