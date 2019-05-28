package jp.co.sample.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.sample.domain.Administrator;

/**
 * @author ryuheisugita
 * 
 *administeratorsテーブルを操作するリポジトリ
 */
@Repository
public class AdminisratorRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate template; 
	
	private static final RowMapper<Administrator> ADMINISTRATOR_ROW_MAPPER = (rs,i) ->{
		Administrator administrator = new Administrator();
		administrator.setId(rs.getInt("id"));
		administrator.setName(rs.getString("name"));
		administrator.setMailAddress(rs.getString("mail_address"));
		administrator.setPassword(rs.getString("password"));
		return administrator;
	};
	
	private String tableName = "administrators";
	
	/**
	 * 管理者情報を挿入する
	 * @param administrator
	 */
	public void insert(Administrator administrator) {
		String sql = "insert into " + tableName + " (name,mail_address,password) values(:name,:mail_address,:password)";
		SqlParameterSource param = new BeanPropertySqlParameterSource(administrator);
		template.update(sql, param);
		System.out.println("insert()の呼び出し");
	}
	
	/**
	 * @param mailAddressとpassword
	 * @return 管理者情報
	 */
	public Administrator findByMailAddressAndPassword( String mailAddress, String password) {
		
		String sql = "select id,name,mail_address,password " + tableName 
				       + " where mail_address=:mailAddress and password=:password";
		SqlParameterSource param = new MapSqlParameterSource()
				                                       .addValue("mailAddress", mailAddress).addValue("password", password);
		
		Administrator administrator = new Administrator();
		administrator = template.queryForObject(sql, param, ADMINISTRATOR_ROW_MAPPER);
		System.out.println("findByMailAddressAndPassword()の呼び出し");          //consoleに表示
		return new Administrator();
		
	}

}
