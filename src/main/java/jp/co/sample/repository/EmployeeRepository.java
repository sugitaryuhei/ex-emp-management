package jp.co.sample.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
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
	
	String tableName = "employees";
	
	/**
	 * 従業員一覧を表示するメソッド.
	 * 
	 * @return 従業員一覧を入社日順で表示
	 */
	public List<Employee> findAll(){
		String sql = "select id,name,image,gender,hire_date,mail_address,zip_code,address,telephone,salary,characteristics,dependents_count"
				       + " from " + tableName + " order by hire_date";
		List<Employee> employeeList = template.query(sql, EMPLOYEE_ROW_MAPPER);
		System.out.println("findAll()の呼び出し");
		return employeeList;
	}
	
	/**
	 * 指定されたIDの従業員情報を取得するメソッド.
	 * 
	 * @param id ID
	 * @return 従業員情報 
	 * @throws DataAccessExcreption 1件も検索されない場合に発生
	 */
	public Employee load(Integer id) {
		String sql = "select id,name,image,gender,hire_date,mail_address,zip_code,address,telephone,salary,characteristics,dependents_count"
			           	+ " from " + tableName + " where id=:id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		Employee employee = template.queryForObject(sql, param, EMPLOYEE_ROW_MAPPER);
		System.out.println("load()の呼び出し");
		return employee;
	}
	
	/**
	 * 従業員情報を変更するメソッド.
	 * 
	 * @param employee
	 */
	public void update(Employee employee) {
		String sql = "update " + tableName + " set dependents_count=:dependentsCount where id=:id";
		SqlParameterSource param = new MapSqlParameterSource()
				                                       .addValue("depedents_count", employee.getDependentsCount())
				                                       .addValue("id", employee.getId());
		template.update(sql, param);
		System.out.println("update()の呼び出し");
	}
}
