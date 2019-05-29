package jp.co.sample.controller;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.co.sample.domain.Administrator;
import jp.co.sample.form.InsertAdministratorForm;
import jp.co.sample.form.LoginForm;
import jp.co.sample.service.AdministratorService;

/**
 * 管理者情報を操作するコントローラー.
 * 
 * @author ryuheisugita
 */
@Controller
@RequestMapping("/")
public class AdministratorController {
	
	@Autowired
	private AdministratorService service;
	
	@Autowired
	private HttpSession session;
	
	@ModelAttribute
	public InsertAdministratorForm setUpInsertAdministratorForm() {
		return new InsertAdministratorForm();
	}
	@ModelAttribute
	public LoginForm setUpLoginForm() {
		return new LoginForm();
	}
	
	/**
	 * 管理者登録画面を表示する.
	 *  
	 * @return 管理者登録画面
	 */
	@RequestMapping("/toInsert")
	public String toInsert() {
		return "administrator/insert";
	}
	
	/**
	 * 管理者情報を登録する.
	 * 
	 * @param form 入力された管理者情報
	 * @return　ログイン画面
	 */
	@RequestMapping("/insert")
	public String insert (InsertAdministratorForm form) {
		Administrator administrator = new Administrator();
		BeanUtils.copyProperties(form, administrator);
		service.insert(administrator);
		return "redirect:/";
	}

	/**
	 * ログイン画面を表示.
	 * 
	 * @return ログイン画面
	 */
	@RequestMapping("/")
	public String toLogin() {
		return "administrator/login";
	}
	
	/**
	 * ログイン.
	 * 
	 * @param 入力されたメールアドレスとパスワード
	 * @return　ログイン画面(メールアドレスとパスワードが不正な場合)
	 * 　　　　 　　　従業員一覧画面(メールアドレスとパスワードがデータベースと一致した場合)　
	 */
	@RequestMapping("/login")
	public String login(@Validated LoginForm form, BindingResult result, Model model) {
		
		if(result.hasErrors()) {
			System.out.println("errors");
			return toLogin();
		}
		
		Administrator administrator = service.login(form.getMailAddress(), form.getPassword());
		if(administrator == null) {
			System.out.println("null");
			model.addAttribute("massage", "メールアドレスが不正です");
			return "administrator/login";
		}
		session.setAttribute("administratorName", administrator.getName());
		return "redirect:/employee/showList";
	}
	
	/**
	 * ログアウトをする.
	 * 
	 * @return ログアウト画面
	 */
	@RequestMapping("/logout")
	public String logout() {
		session.invalidate();
		return "redirect:/";
	}
	
}	
