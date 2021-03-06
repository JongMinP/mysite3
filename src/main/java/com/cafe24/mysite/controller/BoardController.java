package com.cafe24.mysite.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cafe24.mysite.service.BoardService;
import com.cafe24.mysite.vo.BoardVo;
import com.cafe24.mysite.vo.UserVo;
import com.cafe24.pager.Page;
import com.cafe24.pager.Pager;
import com.cafe24.security.Auth;
import com.cafe24.security.AuthUser;

@Controller
@RequestMapping("/board")
public class BoardController {

	private static final Log LOG = LogFactory.getLog(BoardController.class);

	@Autowired
	private BoardService service;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model, @Page Pager pager,
			@RequestParam(value = "page" , required = true, defaultValue = "1") int page,
			@RequestParam(value = "kwd", required = true, defaultValue = "") String kwd) {

		pager.setPage(page);
		List<BoardVo> list = service.getListPage(pager, kwd);
		
		for(BoardVo vo : list) {
			System.out.println(vo);
		}

		model.addAttribute("boards", list);
		model.addAttribute("pager", pager);
		model.addAttribute("kwd",kwd);

		return "board/list";
	}

	@Auth
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public String modify(Model model, @RequestParam(value = "no", required = true, defaultValue = "0") Long no) {

		BoardVo vo = service.getBoard(no);

		model.addAttribute("board", vo);

		return "board/modify";
	}

	@Auth
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modify(@ModelAttribute BoardVo vo) {

		service.Modify(vo);

		return "redirect:/board/view?no=" + vo.getNo();
	}

	@Auth
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String write() {

		return "board/write";
	}

	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String write(@ModelAttribute BoardVo vo, @AuthUser UserVo authUser) {

		vo.setUser(authUser);

		service.insert(vo);

		return "redirect:/board/list";
	}

	@Auth
	@RequestMapping(value = "/reply", method = RequestMethod.GET)
	public String reply(@RequestParam(value = "no", required = true, defaultValue = "0") Long no, Model model) {

		model.addAttribute("no", no);
		return "board/reply";
	}

	@Auth
	@RequestMapping(value = "/reply", method = RequestMethod.POST)
	public String reply(@RequestParam(value = "bno", required = true, defaultValue = "0") Long bno,
			@ModelAttribute BoardVo bvo, @AuthUser UserVo authUser) {

		bvo.setUser(authUser);

		service.replyInsert(bno, bvo);

		return "redirect:/board/list";
	}

	@Auth
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(@RequestParam(value = "no", required = true, defaultValue = "0") Long no) {

		service.remove(no);

		return "redirect:/board/list";
	}

	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public String view(@RequestParam(value = "no", required = true, defaultValue = "0") Long no, Model model,
			HttpSession session) {

		BoardVo vo = service.viewCount(no);

		model.addAttribute("board", vo);

		return "board/view";
	}

}
