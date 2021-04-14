package com.capitol.exam;

import com.capitol.exam.controller.PriceController;
import com.capitol.exam.model.Price;
import com.capitol.exam.model.dto.PriceResponseDTO;
import com.capitol.exam.repository.PriceRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Date;

import static com.capitol.exam.controller.util.DateUtil.parseDate;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ExamTest {

    @Autowired
    private PriceRepository priceRepository;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private static String URL;

    @Before
    public void initDb() {
        priceRepository.saveAll(Arrays.asList(
                createPrice(1L,parseDate("2020-06-14-00.00.00"), parseDate("2020-12-31-23.59.59"), 1, 35455L, 0, 35.50, "EUR"),
                createPrice(1L,parseDate("2020-06-14-15.00.00"), parseDate("2020-06-14-18.30.00"), 2, 35455L, 1, 25.45, "EUR"),
                createPrice(1L,parseDate("2020-06-15-00.00.00"), parseDate("2020-06-15-11.00.00"), 3, 35455L, 1, 30.50, "EUR"),
                createPrice(1L,parseDate("2020-06-15-16.00.00"), parseDate("2020-12-31-23.59.59"), 4, 35455L, 1, 38.95, "EUR")
        ));
        URL = "http://localhost:" + port + "/getPrice?";
    }

    @Test
    public void test1() {
        PriceResponseDTO result = restTemplate.getForObject
                (URL + "date=2020-06-14-10.00.00&productId=35455&brandId=1",
                PriceResponseDTO.class);
        assertEquals(35.5, result.getPrice(),0);
        assertEquals(1, result.getPriceList());
        assertEquals(35455L, result.getProductId(), 0);
        assertEquals(1, result.getBrandId(), 0);
        assertEquals(parseDate("2020-06-14-00.00.00"), result.getStartDate());
        assertEquals(parseDate("2020-12-31-23.59.59"), result.getEndDate());
    }

    @Test
    public void test2() {
        PriceResponseDTO result = restTemplate.getForObject
                (URL + "date=2020-06-14-16.00.00&productId=35455&brandId=1",
                        PriceResponseDTO.class);
        assertEquals(25.45, result.getPrice(),0);
        assertEquals(2, result.getPriceList());
        assertEquals(35455L, result.getProductId(), 0);
        assertEquals(1, result.getBrandId(), 0);
        assertEquals(parseDate("2020-06-14-15.00.00"), result.getStartDate());
        assertEquals(parseDate("2020-06-14-18.30.00"), result.getEndDate());
    }

    @Test
    public void test3() {
        PriceResponseDTO result = restTemplate.getForObject
                (URL + "date=2020-06-14-21.00.00&productId=35455&brandId=1",
                        PriceResponseDTO.class);
        assertEquals(35.50, result.getPrice(),0);
        assertEquals(1, result.getPriceList());
        assertEquals(35455L, result.getProductId(), 0);
        assertEquals(1, result.getBrandId(), 0);
        assertEquals(parseDate("2020-06-14-00.00.00"), result.getStartDate());
        assertEquals(parseDate("2020-12-31-23.59.59"), result.getEndDate());
    }

    @Test
    public void test4() {
        PriceResponseDTO result = restTemplate.getForObject
                (URL + "date=2020-06-15-10.00.00&productId=35455&brandId=1",
                        PriceResponseDTO.class);
        assertEquals(30.50, result.getPrice(),0);
        assertEquals(3, result.getPriceList());
        assertEquals(35455L, result.getProductId(), 0);
        assertEquals(1, result.getBrandId(), 0);
        assertEquals(parseDate("2020-06-15-00.00.00"), result.getStartDate());
        assertEquals(parseDate("2020-06-15-11.00.00"), result.getEndDate());
    }

    @Test
    public void test5() {
        PriceResponseDTO result = restTemplate.getForObject
                (URL + "date=2020-06-16-21.00.00&productId=35455&brandId=1",
                        PriceResponseDTO.class);
        assertEquals(38.95, result.getPrice(),0);
        assertEquals(4, result.getPriceList());
        assertEquals(35455L, result.getProductId(), 0);
        assertEquals(1, result.getBrandId(), 0);
        assertEquals(parseDate("2020-06-15-16.00.00"), result.getStartDate());
        assertEquals(parseDate("2020-12-31-23.59.59"), result.getEndDate());
    }

    private Price createPrice(Long brandId, Date startDate, Date endDate,
                              int priceList, Long productId, int priority, double price, String currency) {
        Price priceObj = new Price();
        priceObj.setBrandId(brandId);
        priceObj.setStartDate(startDate);
        priceObj.setEndDate(endDate);
        priceObj.setPriceList(priceList);
        priceObj.setProductId(productId);
        priceObj.setPriority(priority);
        priceObj.setPrice(price);
        priceObj.setCurrency(currency);
        return priceObj;
    }

}
