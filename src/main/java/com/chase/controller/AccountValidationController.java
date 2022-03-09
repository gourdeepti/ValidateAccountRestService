package com.chase.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.chase.bean.Account;
import com.chase.bean.AccountInfoBean;
import com.chase.bean.DataSourceResponse;
import com.chase.bean.ValidAccountResponse;

@RestController
public class AccountValidationController {

	@Value("${datasource.name1}")
	private String dataSourceName1;
	@Value("${datasource.url1}")
	private String dataSourceURL1;
	@Value("${datasource.name2}")
	private String dataSourceName2;
	@Value("${datasource.url2}")
	private String dataSourceURL2;

	@GetMapping("/valid/account")
	public List<ValidAccountResponse> getAccountInfo(@RequestBody AccountInfoBean accountInfoBean) {

		System.out.println(accountInfoBean.getAccountNumber());
		System.out.println(accountInfoBean.getSources());

		Map<String, String> map = new HashMap<>();
		map.put(dataSourceName1, dataSourceURL1);
		map.put(dataSourceName2, dataSourceURL2);

		Account account = new Account();
		account.setAccountNumber(accountInfoBean.getAccountNumber());

		ArrayList<ValidAccountResponse> accountServiceResponse = new ArrayList<>();

		if (accountInfoBean.getSources() != null) {
			Iterator<String> itr = accountInfoBean.getSources().iterator();

			while (itr.hasNext()) {

				String s1 = itr.next();

				ValidAccountResponse accountResponse = new ValidAccountResponse();

				ResponseEntity<DataSourceResponse> forEntity = new RestTemplate().postForEntity(map.get(s1), account,
						DataSourceResponse.class);
				DataSourceResponse dataSourceResponse = forEntity.getBody();

				accountResponse.setSource(s1);
				accountResponse.setValid(dataSourceResponse.isValid());

				accountServiceResponse.add(accountResponse);
			}
		} else {

			Set<Entry<String, String>> entry = map.entrySet();

			while (entry.iterator().hasNext()) {

				Entry ent = entry.iterator().next();

				String key = (String) ent.getKey();
				String value = (String) ent.getValue();

				ValidAccountResponse accountResponse = new ValidAccountResponse();

				ResponseEntity<DataSourceResponse> forEntity = new RestTemplate().postForEntity(value, account,
						DataSourceResponse.class);
				DataSourceResponse dataSourceResponse = forEntity.getBody();

				accountResponse.setSource(key);
				accountResponse.setValid(dataSourceResponse.isValid());

				accountServiceResponse.add(accountResponse);
			}
		}

		return accountServiceResponse;
	}

}
