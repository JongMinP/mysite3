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
	
	public List<GuestBookVo> guestbookList(Long no) {
		List<GuestBookVo> list = dao.getList(no);
		
		return list;
	}

	public GuestBookVo getGusetBookByNo(Long no) {
		GuestBookVo vo = dao.getGusetBook(no);

		return vo;
	}

	public void deleteGuestBook(Long no) {
		dao.delete(no);
	}
	
	public boolean deleteMessage(GuestBookVo vo) {
		
		return dao.delete(vo) == 1;
	}
	

	public void insertGuestBook(GuestBookVo vo) {
		dao.insert(vo);
	}

	public GuestBookVo insertGuestBook2(GuestBookVo guestbookVo) {
		GuestBookVo vo = null;
		
		System.out.println(guestbookVo);
		int count = dao.insert(guestbookVo);
		
		if( count == 1) {
			vo = dao.get(guestbookVo.getNo());
		}
		
		return vo;
	}

}
