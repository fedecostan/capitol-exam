package com.capitol.exam.controller;

import com.capitol.exam.model.Price;
import com.capitol.exam.model.dto.PriceResponseDTO;
import com.capitol.exam.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PriceController {

    @Autowired
    private PriceService priceService;

    @GetMapping("/getPrice")
    public PriceResponseDTO getPrice(@RequestParam String date, @RequestParam Long productId, @RequestParam Long brandId) {
        return priceService.getPrice(date, productId, brandId);
    }

}
