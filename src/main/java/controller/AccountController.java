package controller;

import com.kata.bank.model.Account;
import com.kata.bank.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value= "/accounts")
@AllArgsConstructor

public class AccountController {
    @Autowired
    private final AccountService accountService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Account> findById(@PathVariable String id) {
        return accountService.findById(id);
    }

    @GetMapping("/ClientId/{clientId}")
    List<Account> getAccountByUser(@PathVariable long clientId) {
        return accountService.findAccountsByClient(clientId);
    }

    @GetMapping("/")
    List<Account> getAccounts() {
        return accountService.findAll();
    }


    @PostMapping(value = "/createAccount")
    Account createAccount(@RequestBody Account account) {
        return accountService.createAccount(account);
    }

    @PutMapping("/updateAccount")
    Account updateAccount(@RequestBody Account account) {
        return accountService.updateAccount(account);
    }

    @DeleteMapping("/delete/{accountId}")
    void deleteAccount(@PathVariable String accountId) {
        accountService.deleteAccount(accountId);
    }

}
