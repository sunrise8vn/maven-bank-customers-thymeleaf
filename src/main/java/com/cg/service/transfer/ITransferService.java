package com.cg.service.transfer;

import com.cg.dto.ITransferDTO;
import com.cg.dto.SumFeesAmountDTO;
import com.cg.dto.TransferDTO;
import com.cg.model.Transfer;
import com.cg.service.IGeneralService;

import java.util.Optional;

public interface ITransferService extends IGeneralService<Transfer> {
    Iterable<ITransferDTO> findAllByITransferDTO();

    Optional<TransferDTO> findByIdWithTransferDTO(Long id);

    Optional<SumFeesAmountDTO> sumFeesAmount();
}
