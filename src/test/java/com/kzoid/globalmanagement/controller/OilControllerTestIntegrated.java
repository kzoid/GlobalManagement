package com.kzoid.globalmanagement.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;

import static com.kzoid.globalmanagement.controller.OilController.BASE_PATH;
import static com.kzoid.globalmanagement.controller.OilController.REVENUE_YIELD_PATH;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OilControllerTestIntegrated {

    @LocalServerPort
    private int port;

    private URL base;

    @Autowired
    private TestRestTemplate template;

    @Before
    public void setUp() throws Exception {
        this.base = new URL("http://localhost:" + port + "/");
    }

    @Test
    public void shouldNotFoundRevenueYieldAbc() throws Exception {
        String oilId = "ABC";
        BigDecimal input = BigDecimal.TEN;
        String expect = "404 - Oil Id not found";

        ResponseEntity<String> response = template
            .getForEntity(base.toString() + BASE_PATH + REVENUE_YIELD_PATH + "/" + oilId + "/" + input, String.class);
        assertThat(response.getBody(), equalTo(expect));
    }

    @Test
    public void shouldReturnRevenueYieldForStandardBwo() throws Exception {
        String oilId = "BWO";
        BigDecimal input = BigDecimal.TEN;
        BigDecimal expect = BigDecimal.valueOf(1.7).setScale(2, RoundingMode.FLOOR);

        ResponseEntity<String> response = template
            .getForEntity(base.toString() + BASE_PATH + REVENUE_YIELD_PATH + "/" + oilId + "/" + input, String.class);
        assertThat(response.getBody(), equalTo(expect.toString()));
    }
}
