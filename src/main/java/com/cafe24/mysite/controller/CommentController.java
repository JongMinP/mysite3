package com.cafe24.mysite.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cafe24.mysite.service.CommentService;
import com.cafe24.mysite.vo.CommentVo;
import com.cafe24.mysite.vo.UserVo;

@Controller
@RequestMapping("/comment")
public class CommentController {

	@Autowired
	private CommentService service;

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String comment(@ModelAttribute CommentVo vo, HttpSession session) {

		vo.setUser((UserVo) session.getAttribute("authUser"));

		service.commentInsert(vo);

		return "redirect:/board/view?no=" + vo.getBoardNo();

	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(@ModelAttribute CommentVo vo) {

		service.commentDelete(vo.getNo());

		return "redirect:/board/view?no=" + vo.getBoardNo();
	}

}
