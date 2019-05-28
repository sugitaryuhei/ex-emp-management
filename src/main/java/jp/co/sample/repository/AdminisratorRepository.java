package jp.co.sample.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.sample.domain.Administrator;

/**
 * administeratorsテーブルを操作するリポジトリ.
 * 
 * @author ryuheisugita
 * 
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
	 * 管理者情報を挿入する.
	 * 
	 * @param administrator 管理者情報
	 */
	public void insert(Administrator administrator) {
		String sql = "insert into " + tableName + " (name,mail_address,password) values(:name,:mail_address,:password)";
		SqlParameterSource param = new BeanPropertySqlParameterSource(administrator);
		template.update(sql, param);
		System.out.println("insert()の呼び出し");
	}
	
	/**
	 * メールアドレスとパスワードから管理者情報を検索する.
	 * 
	 * @param mailAddress メールアドレス
	 * @param password　パスワード
	 * @return 管理者情報　(1件も検索されない場合はnullが返る)
	 */
	public Administrator findByMailAddressAndPassword( String mailAddress, String password) {
		System.out.println("findByMailAddressAndPassword()の呼び出し");          //consoleに表示
		
		String sql = "select id,name,mail_address,password  FROM " + tableName 
				       + " where mail_address=:mail_address and password=:password";
		SqlParameterSource param = new MapSqlParameterSource()
				                                       .addValue("mail_address", mailAddress).addValue("password", password);
		
//		Administrator administrator = new Administrator();
		try {
			Administrator administrator = template.queryForObject(sql, param, ADMINISTRATOR_ROW_MAPPER);
			return administrator;
		}catch (DataAccessException e){
			return null;
		} 
		
	}

}
