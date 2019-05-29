package jp.co.sample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Employee;
import jp.co.sample.form.UpdateEmployeeForm;
import jp.co.sample.service.EmployeeService;

/**
 * 従業員情報一覧を表示するためのコントローラー.
 * 
 * @author ryuheisugita
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeService service;
	
	/**
	 * 従業員情報の一覧を表示する.
	 * 
	 * @param model 従業員情報の一覧
	 * @return 従業員情報一覧画面
	 */
	@RequestMapping("/showList")
	public String showList(Model model) {
		List<Employee> employeeList = service.showList();
		model.addAttribute("employeeList", employeeList);
		System.out.println("employeeList");
		return "employee/list";
	}
	
	@Autowired
	private UpdateEmployeeForm setUpUpdateEmployeeForm() {
		return new UpdateEmployeeForm();
	}
	
	/**
	 * 従業員詳細画面を表示する.
	 * 
	 * @param id　ID
	 * @return 従業員詳細画面
	 */
	@RequestMapping("/showDetail")
	public String showDetail (String id, Model model) {
		Employee employee = service.showDetail(Integer.parseInt(id));
		model.addAttribute("employee", employee);
		System.out.println("detail");
		return "employee/detail";
	}

}
