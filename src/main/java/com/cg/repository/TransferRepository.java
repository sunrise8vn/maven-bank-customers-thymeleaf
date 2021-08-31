package com.cg.repository;

import com.cg.dto.ITransferDTO;
import com.cg.dto.SumFeesAmountDTO;
import com.cg.dto.TransferDTO;
import com.cg.model.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransferRepository extends JpaRepository<Transfer, Long> {

    @Query("SELECT NEW com.cg.dto.TransferDTO (c.id, c.fullName, c.email, c.balance) FROM Customer c WHERE c.id = ?1 ")
    Optional<TransferDTO> findByIdWithTransferDTO(Long id);

    @Query("SELECT " +
            "t.id AS id, " +
            "t.senderId AS senderId, " +
            "c1.fullName as senderName, " +
            "t.recipientId AS recipientId, " +
            "c2.fullName as recipientName, " +
            "t.transferAmount AS transferAmount, " +
            "t.fees AS fees, " +
            "t.feesAmount AS feesAmount " +
            "FROM Transfer t " +
            "LEFT JOIN Customer c1 " +
            "ON t.senderId = c1.id " +
            "LEFT JOIN Customer c2 " +
            "ON t.recipientId = c2.id"
    )
    Iterable<ITransferDTO> findAllByITransferDTO();


    @Query("SELECT NEW com.cg.dto.SumFeesAmountDTO (SUM(t.feesAmount)) FROM Transfer t ")
    Optional<SumFeesAmountDTO> sumFeesAmount();
}
