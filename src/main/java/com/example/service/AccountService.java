package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;
import com.example.resource.RequestAmount;

@Service
public class AccountService {

	/*----------------------------------*/
	/*変数の宣言                        */
	/*----------------------------------*/
	private final AccountRepository accountRepository;

	/*----------------------------------*/
	/*コンストラクタインジェクション    */
	/*----------------------------------*/
	@Autowired
	public AccountService(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	/*--------------------------------*/
	/*口座全件取得                    */
	/*--------------------------------*/
	public List<Account> findAll(){
		return this.accountRepository.findAll();
	}

	/*----------------------------------*/
	/*新規口座開設                      */
	/*----------------------------------*/
	public Account accountCreate() {

		Account account = new Account();

		account.setAmount(0);

		return this.accountRepository.save(account);
	}

	/*--------------------------------*/
	/*対象口座の取得                  */
	/*--------------------------------*/
	public Account getAmount(Integer accountId){
		Optional<Account> optionalaccount = this.accountRepository.findById(accountId);
		Account account = optionalaccount.get();

		return account;

	}

	/*--------------------------------*/
	/*預け入れ(入金)                  */
	/*--------------------------------*/
	public Account deposit(Integer accountId, RequestAmount requestAmount) {

		Account account = this.getAmount(accountId);

		account.setAmount(this.getAmount(accountId).getAmount() + requestAmount.getAmount());

		return this.accountRepository.save(account);
	}

	/*--------------------------------*/
	/*引き出し(出金)                  */
	/*--------------------------------*/
	public Account withdraw(Integer accountId, RequestAmount requestAmount) {
		Account account = this.getAmount(accountId);

		if(this.getAmount(accountId).getAmount() >= requestAmount.getAmount()) {
			account.setAmount(this.getAmount(accountId).getAmount() - requestAmount.getAmount());

		}

		return this.accountRepository.save(account);
	}


}
