package com.cafe24.mysite.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cafe24.mysite.service.GuestbookService;
import com.cafe24.mysite.vo.GuestBookVo;

@Controller
@RequestMapping("/guestbook")
public class GuestbookController {

	private static final Log LOG = LogFactory.getLog(GuestbookController.class);
	
	@Autowired
	private GuestbookService service;

	@RequestMapping("/list")
	public String list(Model model) {

		List<GuestBookVo> list = service.guestbookList();

		model.addAttribute("list", list);
		return "guestbook/list";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(Model model, @RequestParam("no") int no) {
		model.addAttribute("no", no);

		return "guestbook/delete";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(@RequestParam("no") Long no, @RequestParam("password") String password) {

		GuestBookVo vo = service.getGusetBookByNo(no);
		if (password.equals(vo.getPassword())) {
			service.deleteGuestBook(no);
		}

		return "redirect:/guestbook/list";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(@ModelAttribute GuestBookVo vo) {

		service.insertGuestBook(vo);
		return "redirect:/guestbook/list";
	}

}
