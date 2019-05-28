package jp.co.sample.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import jp.co.sample.domain.Administrator;
import jp.co.sample.domain.Employee;

/**
 * @author ryuheisugita
 * 
 *employeesテーブルを操作するリポジトリ
 */
@Repository
public class EmployeeRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate template; 
	
	private static final RowMapper<Employee> EMPLOYEE_ROW_MAPPER = (rs,i) ->{
		Employee employee = new Employee();
		employee.setId(rs.getInt("id"));
		employee.setName(rs.getString("name"));
		employee.setImage(rs.getString("image"));
		employee.setGender(rs.getString("gender"));
		employee.setHireDate(rs.getDate("hire_date"));
		employee.setMailAddress(rs.getString("mail_address"));
		employee.setZipCode(rs.getString("zip_code"));
		employee.setAddress(rs.getString("address"));
		employee.setTelephone(rs.getString("telephone"));
		employee.setSalary(rs.getInt("salary"));
		employee.setCharacteristics(rs.getString("characteristics"));
		employee.setDependentsCount(rs.getInt("dependents_count"));
		return employee;
	};
	
	/**
	 * @return 従業員一覧を入社日順で表示
	 */
	public List<Employee> findAll(){
		System.out.println("findAll()の呼び出し");
		return null;
	}
	
	/**
	 * @param id
	 * @return 検索された従業員情報
	 */
	public Employee load(Integer id) {
		System.out.println("load()の呼び出し");
		return new Employee();
	}
	
	/**
	 * 従業員情報の変更
	 * @param employee
	 */
	public void update(Employee employee) {
		System.out.println("update()の呼び出し");
	}
}
