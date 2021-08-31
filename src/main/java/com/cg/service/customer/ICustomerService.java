package com.cg.service.customer;

import com.cg.model.dto.DepositDTO;
import com.cg.model.dto.RecipientDTO;
import com.cg.model.dto.TransferDTO;
import com.cg.model.dto.WithdrawDTO;
import com.cg.model.Customer;
import com.cg.service.IGeneralService;

import java.math.BigDecimal;
import java.util.Optional;

public interface ICustomerService extends IGeneralService<Customer> {
    Boolean existsByEmail(String email);

    Iterable<Customer> findAllByDeletedIsFalse();

    Optional<DepositDTO> findByIdWithDepositDTO(Long id);

    Optional<WithdrawDTO> findByIdWithWithdrawDTO(Long id);

    Iterable<RecipientDTO> findAllRecipientDTOByIdWithOutSender(Long id);

    Iterable<RecipientDTO> findAllRecipientDTOByIdWithOutSenderAndDeletedIsFalse(Long id);

    void doDeposit(Long customerId, BigDecimal transactionAmount, DepositDTO depositDTO);

    void doWithdraw(Long customerId, BigDecimal transactionAmount, WithdrawDTO withdrawDTO);

    void doTransfer(TransferDTO transferDTO);

    void incrementBalance(BigDecimal balance, Long id);

    void reduceBalance(BigDecimal balance, Long id);

}
