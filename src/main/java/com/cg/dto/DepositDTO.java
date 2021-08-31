package com.cg.dto;

import com.cg.model.Deposit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepositDTO implements Validator {

    private long customerId;
    private String fullName;
    private BigDecimal balance;

    @NotNull(message = "The transaction amount is required.")
    @DecimalMin(value = "49", message = "Transaction Amount must be greater than or equal to 50", inclusive = false)
    @DecimalMax(value = "10000001", message = "Transaction Amount must be less than or equal to 10.000.000", inclusive = false)
    private BigDecimal transactionAmount;

    public DepositDTO(long customerId, String fullName, BigDecimal balance) {
        this.customerId = customerId;
        this.fullName = fullName;
        this.balance = balance;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return DepositDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        DepositDTO depositDTO = (DepositDTO) target;
        BigDecimal transactionAmount = depositDTO.getTransactionAmount();

//        ValidationUtils.rejectIfEmpty(errors, "transactionAmount", "transactionAmount.empty");

        if (transactionAmount != null) {
            if (transactionAmount.toString().length() > 9){
                errors.rejectValue("transactionAmount", "transactionAmount.length");
            }

            if (!transactionAmount.toString().matches("(^$|[0-9]*$)")){
                errors.rejectValue("transactionAmount", "transactionAmount.matches");
            }
        } else {
            errors.rejectValue("transactionAmount", "transactionAmount.null");
        }
    }

    public Deposit toDeposit() {
        return new Deposit()
            .setCustomerId(customerId)
            .setTransactionAmount(transactionAmount);
    }

}
