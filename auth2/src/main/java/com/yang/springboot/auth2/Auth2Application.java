package com.yang.springboot.auth2;

import com.yang.springboot.auth2.entity.Account;
import com.yang.springboot.auth2.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Auth2Application {

	public static void main(String[] args) {
		SpringApplication.run(Auth2Application.class, args);
	}

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	public void init() {
		try {
			Account account = new Account();
			account.setName("yangxinzhao");
			account.setPassword("111");
			account.setRoles(new String[]{"ROLE_USER"});
			accountRepository.deleteAll();
			accountRepository.save(account);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
