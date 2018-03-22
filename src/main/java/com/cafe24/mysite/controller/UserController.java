package com.cafe24.mysite.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cafe24.mysite.service.UserService;
import com.cafe24.mysite.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join() {
		return "user/join";
	}

	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(@ModelAttribute UserVo vo) {
		userService.join(vo);

		return "redirect:joinsuccess";
	}

	@RequestMapping(value = "/joinsuccess")
	public String joinsuccess() {
		return "user/joinsuccess";

	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "user/login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@ModelAttribute UserVo vo, Model model, HttpSession session) {
		UserVo authUser = userService.getUser(vo);

		if (authUser == null) {
			model.addAttribute("result", "fail");
			return "user/login";
		}

		// 인증 처리
		session.setAttribute("authUser", authUser);

		return "redirect:/main";
	}

	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("authUser");
		session.invalidate();

		return "redirect:/main";
	}

	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public String modify(HttpSession session, Model model) {

		/* 접근제어 */
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if (authUser == null) {
			return "redirect:/main";
		}
		
		UserVo vo =userService.getNo(authUser.getNo());
		
		model.addAttribute("user",vo);

		return "user/modify";
	}

	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modify(@ModelAttribute UserVo vo, HttpSession session) {

		/* 접근제어 */  // 이상하게함
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if (authUser == null) {
			return "redirect:/main";
		}
		vo.setNo(authUser.getNo());
		userService.userModify(vo);

		return "redirect:modifysuccess";
	}

	@RequestMapping(value = "/modifysuccess")
	public String modifysuccess() {
		return "user/modifysuccess";

	}

	/*
	 * @ExceptionHandler( UserDaoException.class ) public String
	 * handleUserDaoException() { return "error/error"; }
	 */

}
