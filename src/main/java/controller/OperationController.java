package controller;

import com.kata.bank.model.Operation;
import com.kata.bank.service.impl.OperationServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping(value = "/operations", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class OperationController
{
    @Autowired
    private final OperationServiceImpl operationServiceImpl;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Operation> findById(@PathVariable long id) {
        return operationServiceImpl.findById(id);
    }

    @GetMapping("/accountId/{accountId}")
    List<Operation> getOperationByAccount(@PathVariable String accountId) {
        return operationServiceImpl.findAllOperationsByAccount(accountId);
    }

    @GetMapping("/")
    List<Operation> getOperations() {
        return operationServiceImpl.findAll();
    }

    @PostMapping("/deposit")
    void deposit(@RequestBody  String accountId, @RequestBody double amount) {
        operationServiceImpl.deposit(accountId, amount);
    }

    @PostMapping("/withdrawal")
    void withdrawal(@RequestBody String accountId, @RequestBody double amount) {
        operationServiceImpl.withdrawal(accountId, amount);
    }


    @PostMapping("/payment")
    void payment(@RequestBody String accountTransmitter, @RequestBody String accountBeneficiary, @RequestBody double amount) {
        operationServiceImpl.payment(accountTransmitter, accountBeneficiary, amount);
    }
}
