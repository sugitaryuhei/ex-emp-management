package jp.co.sample.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Administrator;
import jp.co.sample.form.InsertAdministratorForm;
import jp.co.sample.form.LoginForm;
import jp.co.sample.service.AdministratorService;

/**
 * @author ryuheisugita
 *管理者情報を操作するコントローラー
 */
@Controller
@RequestMapping("/")
public class AdministratorController {
	
	@Autowired
	private AdministratorService service;
	
	@ModelAttribute
	public InsertAdministratorForm setUpInsertAdministratorForm() {
		return new InsertAdministratorForm();
	}
	@ModelAttribute
	public LoginForm setUpLoginForm() {
		return new LoginForm();
	}
	
    //管理者登録画面を表示
	@RequestMapping("/toInsert")
	public String toInsert() {
		return "administrator/insert";
	}
	
	//管理者情報を登録する
	@RequestMapping("/insert")
	public String insert (InsertAdministratorForm form) {
		//管理者情報をドメインにコピー
		Administrator administrator = new Administrator();
		BeanUtils.copyProperties(form, administrator);
		//管理者情報を登録
		service.insert(administrator);
		return "redirect:/";
	}
	
	//ログイン画面を表示
	@RequestMapping("/")
	public String toLogin() {
		return "admistrator/login";
	}

}
