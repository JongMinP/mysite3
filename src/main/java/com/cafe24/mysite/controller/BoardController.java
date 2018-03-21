package com.cafe24.mysite.controller;

import java.util.List;

import javax.naming.NameClassPair;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cafe24.mysite.service.BoardService;
import com.cafe24.mysite.vo.BoardVo;
import com.cafe24.mysite.vo.Pager;
import com.cafe24.mysite.vo.UserVo;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService service;

	//
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		List<BoardVo> list = service.getListPage();

		Pager pager = service.getPager();

		model.addAttribute("boards", list);
		model.addAttribute("pager", pager);

		return "board/list";
	}

	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public String modify(Model model, @RequestParam("no") Long no) {

		BoardVo vo = service.getBoard(no);

		model.addAttribute("board", vo);

		return "board/modify";
	}

	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modify(@ModelAttribute BoardVo vo) {

		service.Modify(vo);

		return "redirect:/board/view?no=" + vo.getNo();
	}

	//

	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String write() {

		return "board/write";
	}

	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String write(@ModelAttribute BoardVo vo, HttpSession session) {

		UserVo authUser = (UserVo) session.getAttribute("authUser");
		vo.setUser(authUser);

		service.insert(vo);

		return "redirect:/board/list";
	}

	//

	@RequestMapping(value = "/reply", method = RequestMethod.GET)
	public String reply(@RequestParam("no") int no, Model model) {

		model.addAttribute("no", no);
		return "board/reply";
	}

	@RequestMapping(value = "/reply", method = RequestMethod.POST)
	public String reply(@RequestParam("bno") Long bno, @ModelAttribute BoardVo bvo, HttpSession session) {

		bvo.setUser((UserVo) session.getAttribute("authUser"));

		service.replyInsert(bno, bvo);

		return "redirect:/board/list";
	}

	//
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(@ModelAttribute BoardVo vo) {

		service.remove(vo);

		return "redirect:/board/list";
	}

	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public String view(@RequestParam("no") Long no, Model model, HttpSession session) {

		BoardVo vo = service.viewCount(no);

		model.addAttribute("board", vo);

		return "board/view";
	}

	//

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String search(@RequestParam("kwd") String kwd, Model model) {

		List<BoardVo> list = service.searchList(kwd);

		Pager pager = new Pager();
		pager.setTotalCount(service.searchTotalCount(kwd));
		pager.setIndexCount(service.searchTotalCount(kwd));

		model.addAttribute("boards", list);
		model.addAttribute("kwd", kwd);
		model.addAttribute("pager", pager);

		return "board/list";
	}

	@RequestMapping(value = "/pager", method = RequestMethod.GET)
	public String pager(@ModelAttribute Pager pager, @RequestParam("kwd") String kwd, Model model) {

		if (kwd == null) {
			kwd = "";
		}
		Pager npager = new Pager();
		npager.setPage(pager.getPage());
		npager.setCurrentPage(pager.getCurrentPage());
		npager.setTotalCount(pager.getTotalCount());
		npager.setIndexCount(pager.getIndexCount());

		List<BoardVo> list = service.pagerList(kwd, pager);

		model.addAttribute("boards", list);
		model.addAttribute("kwd", kwd);
		model.addAttribute("pager", npager);

		return "board/list";
	}

	@RequestMapping(value = "/arrow", method = RequestMethod.GET)
	public String arrow(@ModelAttribute Pager pager, @RequestParam("kwd") String kwd, Model model) {

		if (kwd == null) {
			kwd = "";
		}

		Pager npager = new Pager();
		npager.setPage(pager.getPage());
		npager.setCurrentPage(npager.getPageStart());
		npager.setTotalCount(service.getTotalCount());
		npager.setIndexCount(pager.getIndexCount());

		List<BoardVo> list = service.arrowList(kwd, pager);

		model.addAttribute("boards", list);
		model.addAttribute("pager", npager);
		model.addAttribute("kwd", kwd);

		return "board/list";
	}

}
