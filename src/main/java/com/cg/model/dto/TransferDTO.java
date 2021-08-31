package com.cg.model.dto;

import com.cg.model.Transfer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransferDTO implements Validator {

    private Long senderId;
    private String senderName;
    private String email;
    private Long recipientId;
    private BigDecimal balance;

    @NotNull(message = "The transfer amount is required")
    @DecimalMin(value = "49", message = "Transaction Amount must be greater than or equal to 50", inclusive = false)
    @DecimalMax(value = "10000001", message = "Transaction Amount must be less than or equal to 10.000.000", inclusive = false)
    private BigDecimal transferAmount;

    private int fees;
    private BigDecimal feesAmount;
    private BigDecimal transactionAmount;

    public TransferDTO(long id, String fullName, String email, BigDecimal balance) {
        this.senderId = id;
        this.senderName = fullName;
        this.email = email;
        this.balance = balance;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return TransferDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        TransferDTO transferDTO = (TransferDTO) target;
        BigDecimal transferAmount = transferDTO.getTransferAmount();

        if (transferAmount != null) {
            if (transferAmount.toString().length() > 9){
                errors.rejectValue("transferAmount", "transferAmount.length");
            }

            if (!transferAmount.toString().matches("(^$|[0-9]*$)")){
                errors.rejectValue("transferAmount", "transferAmount.matches");
            }
        } else {
            errors.rejectValue("transferAmount", "transferAmount.null");
        }
    }

    public Transfer toTransfer() {
        return new Transfer()
                .setSenderId(senderId)
                .setRecipientId(recipientId)
                .setTransferAmount(transferAmount)
                .setFees(fees)
                .setFeesAmount(feesAmount)
                .setTransactionAmount(transactionAmount);
    }

}
