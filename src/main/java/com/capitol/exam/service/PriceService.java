package com.capitol.exam.service;

import com.capitol.exam.model.dto.PriceResponseDTO;

public interface PriceService {

    PriceResponseDTO getPrice(String date, Long productId, Long brandId);

}
