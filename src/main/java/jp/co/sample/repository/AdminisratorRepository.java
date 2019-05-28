package jp.co.sample.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
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
	
	/**
	 * 管理者情報を挿入する
	 * @param administrator
	 */
	public void insert(Administrator administrator) {
		System.out.println("insert()の呼び出し");
	}
	
	/**
	 * @param mailAddress
	 * @param password
	 * @return Administrator
	 */
	public Administrator findByMailAddressAndPassword( String mailAddress, String password) {
		System.out.println("findByMailAddressAndPassword()の呼び出し");
		return new Administrator();
	}

}
