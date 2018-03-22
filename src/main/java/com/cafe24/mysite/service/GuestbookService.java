package com.cafe24.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.mysite.repository.GuestBookDao;
import com.cafe24.mysite.vo.GuestBookVo;

@Service
public class GuestbookService {

	@Autowired
	private GuestBookDao dao;

	public List<GuestBookVo> guestbookList() {
		List<GuestBookVo> list = dao.getList();

		return list;
	}

	public GuestBookVo getGusetBookByNo(Long no) {
		GuestBookVo vo = dao.getGusetBook(no);

		return vo;
	}

	public void deleteGuestBook(Long no) {
		dao.delete(no);
	}

	public void insertGuestBook(GuestBookVo vo) {
		dao.insert(vo);
	}

}
