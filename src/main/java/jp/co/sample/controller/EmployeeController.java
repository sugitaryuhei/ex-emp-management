package jp.co.sample.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Employee;
import jp.co.sample.form.EmployeeForm;
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
	
	/**
	 * 扶養人数を更新する.
	 * 
	 * @param form IDと更新する不要人数の情報
	 * @return　従業員一覧画面
	 */
	@RequestMapping("/update")
	public String update(UpdateEmployeeForm form) {
		Employee employee = service.showDetail(form.getId());
		employee.setDependentsCount(form.getDependentsCount());
		service.update(employee);
		return "redirect:/employee/showList";
	}
	
	
	/**
	 * 従業員情報登録画面を表示するメソッド.
	 * 
	 * @return 従業員情報登録画面
	 */
	@RequestMapping("/toAdd")
	public String toAdd() {
		return "employee/add";
	}
	
	@Autowired
	private EmployeeForm setUpEmployeeForm() {
		return new EmployeeForm();
	}
	/**
	 * 従業員登録を行う.
	 * 
	 * @param form 従業員情報を受け取るフォーム
	 * @return　従業員登録
	 */
	@RequestMapping("/add")
	public String add(EmployeeForm form) {
		System.out.println(form);
		Employee employee = new Employee();
		BeanUtils.copyProperties(form, employee);
		Date date = (Date) java.sql.Date.valueOf(form.getHireDate());
		employee.setHireDate(date);
		System.out.println(employee);
		service.insert(employee);
		return "redirect:/employee/showList";
	}

}
