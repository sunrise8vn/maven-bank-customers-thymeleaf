package com.cg.service.transfer;

import com.cg.model.dto.ITransferDTO;
import com.cg.model.dto.SumFeesAmountDTO;
import com.cg.model.dto.TransferDTO;
import com.cg.model.Transfer;
import com.cg.service.IGeneralService;

import java.util.Optional;

public interface ITransferService extends IGeneralService<Transfer> {
    Iterable<ITransferDTO> findAllByITransferDTO();

    Optional<TransferDTO> findByIdWithTransferDTO(Long id);

    Optional<SumFeesAmountDTO> sumFeesAmount();
}
