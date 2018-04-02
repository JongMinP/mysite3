package com.cafe24.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.mysite.repository.BoardDao;
import com.cafe24.mysite.repository.CommentDao;
import com.cafe24.mysite.vo.BoardVo;
import com.cafe24.pager.Pager;

@Service
public class BoardService {

	@Autowired
	private BoardDao dao;

	@Autowired
	private CommentDao cDao;

	public List<BoardVo> getListPage(Pager pager, String kwd) {

		pager.pagination(pager.getPage(), getTotalCount(kwd));

		List<BoardVo> list = dao.getListPage(pager, kwd);

		return list;
	}

	public int getTotalCount(String kwd) {

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

		vo.setOrderNo((long) 1);
		vo.setDepth((long) 0);
		vo.setCount((long) 0);

		dao.insert(vo);
	}

	public void replyInsert(Long bno, BoardVo bvo) {

		BoardVo vo = getBoard(bno);

		bvo.setGroupNo(vo.getGroupNo());
		bvo.setOrderNo(vo.getOrderNo() + 1);
		bvo.setDepth(vo.getDepth() + 1);
		bvo.setCount(0L);
		dao.updateOrder(vo.getGroupNo(), vo.getOrderNo() + 1);

		dao.insert(bvo);

	}

	public void remove(Long no) {

		cDao.boardDelete(no); // 보드에 해달 되는 댓글 지우고

		dao.delete(no); // 보드 지우기

	}

	public BoardVo viewCount(Long no) {
		BoardVo vo = getBoard(no);

		vo.setCount(vo.getCount() + 1);

		dao.update(vo);

		return vo;

	}

}
