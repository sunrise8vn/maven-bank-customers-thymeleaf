package com.cg.service.customer;

import com.cg.model.dto.DepositDTO;
import com.cg.model.dto.RecipientDTO;
import com.cg.model.dto.TransferDTO;
import com.cg.model.dto.WithdrawDTO;
import com.cg.model.Customer;
import com.cg.repository.CustomerRepository;
import com.cg.repository.DepositRepository;
import com.cg.repository.TransferRepository;
import com.cg.repository.WithdrawRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;


@Service
@Transactional
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private DepositRepository depositRepository;

    @Autowired
    private WithdrawRepository withdrawRepository;

    @Autowired
    private TransferRepository transferRepository;

    @Override
    public Iterable<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Iterable<Customer> findAllByDeletedIsFalse() {
        return customerRepository.findAllByDeletedIsFalse();
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return customerRepository.existsByEmail(email);
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public void remove(Long id) {
        customerRepository.deleteById(id);
    }

    @Override
    public Optional<DepositDTO> findByIdWithDepositDTO(Long id) {
        return customerRepository.findByIdWithDepositDTO(id);
    }

    @Override
    public Optional<WithdrawDTO> findByIdWithWithdrawDTO(Long id) {
        return customerRepository.findByIdWithWithdrawDTO(id);
    }

    @Override
    public Iterable<RecipientDTO> findAllRecipientDTOByIdWithOutSender(Long id) {
        return customerRepository.findAllRecipientDTOByIdWithOutSender(id);
    }

    @Override
    public Iterable<RecipientDTO> findAllRecipientDTOByIdWithOutSenderAndDeletedIsFalse(Long id) {
        return customerRepository.findAllRecipientDTOByIdWithOutSenderAndDeletedIsFalse(id);
    }

    @Override
    public void doDeposit(Long customerId, BigDecimal transactionAmount, DepositDTO depositDTO) {
        customerRepository.incrementBalance(transactionAmount, customerId);

//        Deposit deposit = new Deposit();
//        deposit.setCustomerId(customerId);
//        deposit.setTransactionAmount(transactionAmount);
        depositRepository.save(depositDTO.toDeposit());
    }

    @Override
    public void doWithdraw(Long customerId, BigDecimal transactionAmount, WithdrawDTO withdrawDTO) {
        customerRepository.reduceBalance(transactionAmount, customerId);

        withdrawRepository.save(withdrawDTO.toWithdraw());
    }

    @Override
    public void doTransfer(TransferDTO transferDTO) {
        customerRepository.reduceBalance(transferDTO.getTransactionAmount(), transferDTO.getSenderId());

        customerRepository.incrementBalance(transferDTO.getTransferAmount(), transferDTO.getRecipientId());

        transferRepository.save(transferDTO.toTransfer());
    }

    @Override
    public void incrementBalance(BigDecimal balance, Long id) {
        customerRepository.incrementBalance(balance, id);
    }

    @Override
    public void reduceBalance(BigDecimal balance, Long id) {
        customerRepository.reduceBalance(balance, id);
    }
}
