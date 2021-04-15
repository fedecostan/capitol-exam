package com.capitol.exam.service.impl;

import com.capitol.exam.model.Price;
import com.capitol.exam.model.dto.PriceResponseDTO;
import com.capitol.exam.repository.PriceRepository;
import com.capitol.exam.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static com.capitol.exam.controller.util.DateUtil.parseDate;

@Service
public class PriceServiceImpl implements PriceService {

    @Autowired
    private PriceRepository priceRepository;

    @Override
    public PriceResponseDTO getPrice(String date, Long productId, Long brandId) {
        Date parsedDate = parseDate(date);
        List<Price> result = priceRepository.findByBrandIdAndProductIdAndStartDateLessThanAndEndDateGreaterThan(
                brandId, productId, parsedDate, parsedDate);
        if (result.size()>0) {
            Price price = result.get(0);
            for (int i = 1; i < result.size(); i++) {
                if (result.get(i).getPriority() > price.getPriority())
                    price = result.get(i);
            }
            return priceToPriceResponseDTO(price);
        }
        return null;
    }

    private PriceResponseDTO priceToPriceResponseDTO(Price price) {
        return new PriceResponseDTO(price.getProductId(), price.getBrandId(), price.getPriceList(),
                price.getStartDate(), price.getEndDate(), price.getPrice());
    }
}
