package com.cg.service.transfer;

import com.cg.dto.ITransferDTO;
import com.cg.dto.SumFeesAmountDTO;
import com.cg.dto.TransferDTO;
import com.cg.model.Transfer;
import com.cg.repository.TransferRepository;
import com.cg.service.customer.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class TransferServiceImpl implements ITransferService {

    @Autowired
    private TransferRepository transferRepository;

    @Override
    public Iterable<Transfer> findAll() {
        return null;
    }

    @Override
    public Optional<Transfer> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<TransferDTO> findByIdWithTransferDTO(Long id) {
        return transferRepository.findByIdWithTransferDTO(id);
    }

    @Override
    public Iterable<ITransferDTO> findAllByITransferDTO() {
        return transferRepository.findAllByITransferDTO();
    }

    @Override
    public Optional<SumFeesAmountDTO> sumFeesAmount() {
        return transferRepository.sumFeesAmount();
    }

    @Override
    public Transfer save(Transfer transfer) {
        return transferRepository.save(transfer);
    }

    @Override
    public void remove(Long id) {

    }
}
