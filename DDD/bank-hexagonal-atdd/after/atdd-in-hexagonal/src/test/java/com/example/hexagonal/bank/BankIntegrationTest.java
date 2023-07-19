package com.example.hexagonal.bank;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import java.math.BigDecimal;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import com.example.hexagonal.bank.adapter.out.persistence.BankAmountSpringDataRepository;
import com.example.hexagonal.bank.application.web.DepositRequest;
import com.example.hexagonal.bank.application.web.DepositResponse;
import com.example.hexagonal.bank.application.web.WithDrawRequest;
import com.example.hexagonal.bank.application.web.WithDrawResponse;
import com.example.hexagonal.bank.fixture.BankFixture;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class BankIntegrationTest {
	@Autowired
	ObjectMapper objectMapper;

	@Autowired
	MockMvc mockMvc;

	@Autowired
	BankAmountSpringDataRepository repository;

	@Autowired
	BankFixture bankFixture;

	@BeforeEach
	void setUp() {
	     repository.deleteAll();
	}


	@Test
	void 입금_요청() throws Exception {
		//given
		BigDecimal depositAmount = BigDecimal.valueOf(100);
		String customer = "joongseok";
		DepositRequest request = DepositRequest.of(depositAmount, customer);

		//when
		MockHttpServletResponse response = mockMvc.perform(post("/api/deposit")
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(request))
		).andReturn().getResponse();

		//then
		Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		DepositResponse depositResponse = objectMapper.readValue(response.getContentAsString(), DepositResponse.class);
		Assertions.assertThat(depositResponse.getDepositAmount()).isEqualByComparingTo(request.getDepositAmount());
		Assertions.assertThat(depositResponse.getCustomer()).isEqualTo(customer);
		Assertions.assertThat(depositResponse.getBalance()).isEqualByComparingTo(depositAmount);
	}

	@Test
	void 출금_요청() throws Exception {
		//given
		String customer = "joongseok";
		bankFixture.입금_요청(100, customer);
		BigDecimal withDrawAmount = BigDecimal.valueOf(50);
		WithDrawRequest request = WithDrawRequest.of(withDrawAmount, customer);

		//when
		MockHttpServletResponse response = mockMvc.perform(post("/api/withdraw")
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(request))
		).andReturn().getResponse();

		//then
		Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		WithDrawResponse withDrawResponse = objectMapper.readValue(response.getContentAsString(), WithDrawResponse.class);
		Assertions.assertThat(withDrawResponse.getWithDrawAmount()).isEqualByComparingTo(request.getWithDrawAmount());
		Assertions.assertThat(withDrawResponse.getCustomer()).isEqualTo(customer);
		Assertions.assertThat(withDrawResponse.getBalance()).isEqualByComparingTo(BigDecimal.valueOf(50));
	}

	@Test
	void 출금_가능_금액_초과_요청() {
		String customer = "joongseok";
		int depositAmount = 100;
		int withDrawAmount = 120;
		bankFixture.입금_요청(depositAmount, customer);

		Assertions.assertThatThrownBy(() -> bankFixture.출금_요청(withDrawAmount, customer))
			.isInstanceOf(IllegalArgumentException.class);
	}

}
