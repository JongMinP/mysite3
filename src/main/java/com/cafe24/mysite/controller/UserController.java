package com.cafe24.mysite.controller;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.tomcat.util.net.jsse.openssl.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cafe24.mysite.service.UserService;
import com.cafe24.mysite.vo.UserVo;
import com.cafe24.security.Auth;
import com.cafe24.security.Auth.Role;
import com.cafe24.security.AuthUser;


@Controller
@RequestMapping("/user")
public class UserController {

	private static final Log LOG = LogFactory.getLog(UserController.class);

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

	@Auth
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public String modify(@AuthUser UserVo authUser, Model model) {

		/* 접근제어 */
//		UserVo authUser = (UserVo) session.getAttribute("authUser");
		
		UserVo vo = userService.getNo(authUser.getNo());

		model.addAttribute("user", vo);

		return "user/modify";
	}

	@Auth(role=Auth.Role.USER)
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modify(
			@AuthUser UserVo authUser,
			@ModelAttribute UserVo userVo ) {

		userVo.setNo(authUser.getNo());
		userService.userModify(userVo);
		
//		authUser.setName(userVo.getName());

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
