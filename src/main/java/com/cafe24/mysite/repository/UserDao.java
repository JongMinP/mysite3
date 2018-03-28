package com.cafe24.mysite.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StopWatch;

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
	
	public UserVo getEmail(String email) {

		return sqlSession.selectOne("user.getByEmail", email);
	}

	public UserVo getNo(Long no) {

		return sqlSession.selectOne("user.getByNo", no);
	}

	public UserVo get(UserVo vo) {
		
		return sqlSession.selectOne("user.getByEmailAndPassword", vo);
		
		
		/* 이런식으로 사용 하려면 코드를 고쳐야 한다.*/
//		StopWatch sw = new StopWatch();
//		sw.start();
//		
//		UserVo result = sqlSession.selectOne("user.getByEmailAndPassword", vo);
//		
//		sw.stop();
//		Long totalTime = sw.getTotalTimeMillis();
//		
//		return result;
	}

	public boolean insert(UserVo vo) {
		int count = sqlSession.insert("user.insert", vo);

		return count == 1;
	}

}
