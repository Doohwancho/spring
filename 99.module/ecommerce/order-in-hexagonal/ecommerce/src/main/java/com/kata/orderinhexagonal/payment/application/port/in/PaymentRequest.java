package com.kata.orderinhexagonal.payment.application.port.in;

import com.kata.orderinhexagonal.payment.domain.CardCompany;
import com.kata.orderinhexagonal.payment.domain.CardType;
import com.kata.orderinhexagonal.payment.domain.PaymentType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PaymentRequest {

    @NotNull(message = "주문 번호를 입력해주세요.")
    private Long orderId;
    @NotNull(message = "결제 수단을 선택해주세요.")
    private CardType cardType;
    @NotNull(message = "카드사를 선택해주세요.")
    private CardCompany cardCompany;
    @Pattern(regexp = "^\\d{16}$", message = "카드번호를 정확히 입력해주세요.")
    @NotNull(message = "카드번호를 입력해주세요.")
    private String cardNumber;
    @Pattern(regexp = "^\\d{3}$", message = "카드 뒷면의 cvc 번호를 정확히 입력해주세요.")
    @NotNull(message = "카드 뒷면의 cvc 번호를 입력해주세요.")
    private String cardCvc;
    @NotNull(message = "할부 여부를 입력해주세요.")
    private PaymentType paymentType;

    public PaymentRequest(Long orderId, CardType cardType, CardCompany cardCompany,
                          String cardNumber, String cardCvc, PaymentType paymentType) {
        this.orderId = orderId;
        this.cardType = cardType;
        this.cardCompany = cardCompany;
        this.cardNumber = cardNumber;
        this.cardCvc = cardCvc;
        this.paymentType = paymentType;
    }

    public static PaymentRequest of(Long orderId, CardType cardType, CardCompany cardCompany,
                                    String cardNumber, String cardCvc, PaymentType paymentType) {
        return new PaymentRequest(orderId, cardType, cardCompany, cardNumber, cardCvc, paymentType);
    }
}
