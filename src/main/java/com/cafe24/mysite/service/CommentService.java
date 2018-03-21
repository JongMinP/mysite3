package com.cafe24.mysite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.mysite.repository.CommentDao;
import com.cafe24.mysite.vo.CommentVo;

@Service
public class CommentService {

	@Autowired
	private CommentDao dao;

	public void commentInsert(CommentVo vo) {

		dao.insert(vo);
	}

	public void commentDelete(Long no) {
		
		dao.delete(no);
	}

}
