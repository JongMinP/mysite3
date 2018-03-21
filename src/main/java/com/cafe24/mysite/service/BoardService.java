package com.cafe24.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.mysite.repository.BoardDao;
import com.cafe24.mysite.repository.CommentDao;
import com.cafe24.mysite.vo.BoardVo;
import com.cafe24.mysite.vo.Pager;

@Service
public class BoardService {

	@Autowired
	private BoardDao dao;

	@Autowired
	private CommentDao cDao;

	public List<BoardVo> getListPage() {
		List<BoardVo> list = dao.getListPage(0, 10);

		return list;
	}

	public Pager getPager() {
		Pager pager = new Pager();
		int totalCount = dao.getTotalCount();
		pager.setTotalCount(totalCount);
		pager.setIndexCount(totalCount);

		return pager;
	}

	public List<BoardVo> searchList(String kwd) {

		Pager pager = new Pager();
		List<BoardVo> list = dao.getListSearch(kwd, pager.getPageStart() - 1, 10);

		return list;
	}
	
	
	public List<BoardVo> pagerList(String kwd,Pager pager) {

		List<BoardVo> list = dao.getListSearch(kwd, pager.getPageStart(), 10);

		return list;
	}
	
	
	public List<BoardVo> arrowList(String kwd,Pager pager) {

		List<BoardVo> list = dao.getListSearch(kwd, pager.getPageStart() * 10 - 10, 10);

		return list;
	}
	
	
	public int getTotalCount() {

		return dao.getTotalCount();
	}
	

	public int searchTotalCount(String kwd) {

		return dao.getTotalCount(kwd);
	}

	public BoardVo getBoard(Long no) {
		BoardVo vo = dao.getBoard(no);

		return vo;
	}

	public void Modify(BoardVo vo) {

		BoardVo mvo = getBoard(vo.getNo());

		mvo.setTitle(vo.getTitle());
		mvo.setContent(vo.getContent());

		dao.update(mvo);
	}

	public void insert(BoardVo vo) {

		dao.insert(vo);
	}

	public void replyInsert(Long bno, BoardVo bvo) {

		BoardVo vo = getBoard(bno);

		bvo.setGroupNo(vo.getGroupNo());
		bvo.setOrderNo(vo.getOrderNo() + 1);
		bvo.setDepth(vo.getDepth() + 1);

		dao.updateOrder(vo.getGroupNo(), vo.getOrderNo() + 1);

		dao.replyWrite(bvo);

	}

	public void remove(BoardVo vo) {

		cDao.boardDelete(vo.getNo());

		dao.groupDelete(vo.getGroupNo(), vo.getOrderNo());

		dao.delete(vo.getNo());

	}

	public BoardVo viewCount(Long no) {
		BoardVo vo = getBoard(no);

		vo.setCount(vo.getCount() + 1);

		dao.update(vo);

		return vo;

	}

}
