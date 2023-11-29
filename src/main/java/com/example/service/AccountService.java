package com.example.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;


@Transactional
@Service
public class AccountService 
{
  AccountRepository accountRepository;

 
  public AccountService(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }


/* 
  ## 1: Our API should be able to process new User registrations.

  As a user, I should be able to create a new Account on the endpoint POST localhost:8080/register. The body will contain a 
  representation of a JSON Account, but will not contain an account_id.
- The registration will be successful if and only if the username is not blank, the password is at least 4 characters long, and 
  an Account with that username does not already exist. If all these conditions are met, the response body should contain a JSON
  of the Account, including its account_id. The response status should be 200 OK, which is the default. The new account should 
  be persisted to the database.
- If the registration is not successful due to a duplicate username, the response status should be 409. (Conflict)
- If the registration is not successful for some other reason, the response status should be 400. (Client error)

*/
  public Account addNewUserRegistrationService(Account account){
    Optional<Account> usernameOptional = accountRepository.findByUsername(account.getUsername());
      if(!usernameOptional.isPresent()){
        return accountRepository.save(account);
      }
      return null;
  
  }

 /*
  ## 2: Our API should be able to process User logins.

  As a user, I should be able to verify my login on the endpoint POST localhost:8080/login. The request body will contain a JSON 
  representation of an Account.
  - The login will be successful if and only if the username and password provided in the request body JSON match a real account 
    existing on the database. If successful, the response body should contain a JSON of the account in the response body,including 
    its account_id. The response status should be 200 OK, which is the default.
  - If the login is not successful, the response status should be 401. (Unauthorized)
 */

  public Account addUserLoginService(Account account){
    Optional<Account> usernameOptional = accountRepository.findByUsername(account.getUsername());
    if((usernameOptional.isPresent()) && ((account.getUsername()).equals(usernameOptional.get().getUsername())) && ((account.getPassword()).equals(usernameOptional.get().getPassword()))){
      return usernameOptional.get();
    }
    else
      return null;
  }





}