package jp.co.sample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sample.domain.Employee;
import jp.co.sample.repository.EmployeeRepository;

/**
 * 従業員情報一覧を表示するメソッド.
 * 
 * @author ryuheisugita
 */
@Service
@Transactional
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository repository;
	
	/**
	 * 従業員情報一覧を表示するメソッド.
	 * 
	 * @return 従業員情報一覧
	 */
	public List<Employee> showList(){
		List<Employee> employeeList = repository.findAll();
		return employeeList;
	}

}