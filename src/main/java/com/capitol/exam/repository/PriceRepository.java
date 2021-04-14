package com.capitol.exam.repository;

import com.capitol.exam.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {

    List<Price> findByBrandIdAndProductIdAndStartDateLessThanAndEndDateGreaterThan
            (Long brandId, Long productId, Date startDate, Date endDate);

}
