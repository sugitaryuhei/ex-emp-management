package jp.co.sample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sample.domain.Administrator;
import jp.co.sample.repository.AdminisratorRepository;

/**
 * @author ryuheisugita
 *管理者情報を操作するサービス
 */
@Service
@Transactional
public class AdministratorService {
	
	@Autowired
	private AdminisratorRepository repository;
	
	/**
	 * 管理者情報を登録するメソッド.
	 * 
	 * @param 管理者情報
	 */
	public void insert(Administrator administrator) {
		repository.insert(administrator);
	}
	
	/**
	 * メールアドレスとパスワードから管理者情報をえるメソッド.
	 * 
	 * @param mailAddress　メールアドレス
	 * @param password　パスワード
	 * @return 管理者情報
	 */
	public Administrator login(String mailAddress, String password) {
		Administrator administrator = repository.findByMailAddressAndPassword(mailAddress, password);
		return administrator;
	}

}
