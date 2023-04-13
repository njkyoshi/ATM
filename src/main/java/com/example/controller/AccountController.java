package com.example.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Account;
import com.example.resource.ResponseAmount;
import com.example.service.AccountService;

@RestController
//@RequestMapping(value = "/bankTrading", produces="application/json;charset=UTF-8")
public class AccountController {

	/*----------------------------------*/
	/*変数の宣言                        */
	/*----------------------------------*/
	private final AccountService accountService;


	/*----------------------------------*/
	/*コンストラクタインジェクション    */
	/*----------------------------------*/
	public AccountController(AccountService accountService) {
		this.accountService = accountService;
	}

	/*--------------------------------*/
	/*口座全件取得                    */
	/*--------------------------------*/
	@GetMapping("bankTrading/list")
	public List<Account> list(){
		return this.accountService.findAll();
	}


	/*----------------------------------*/
	/*新規口座開設                      */
	/*----------------------------------*/
	@PostMapping("bankTrading/open")
	public Account open() {
		return this.accountService.accountCreate();
	}

	/*--------------------------------*/
	/*対象口座の取得                  */
	/*--------------------------------*/
	@GetMapping("bankTrading/{account_id}")
	public Integer accountGet(@PathVariable("account_id") Integer accountId) {

		//ResponseAmountインスタンスにセット
		ResponseAmount responseAmount = new ResponseAmount();
		responseAmount.setAmount(this.accountService.getAmount(accountId).getAmount());

		return responseAmount.getAmount();

		//return this.accountService.getAmount(accountId);
	}










}
