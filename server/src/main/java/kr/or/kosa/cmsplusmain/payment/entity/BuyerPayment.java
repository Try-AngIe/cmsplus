package kr.or.kosa.cmsplusmain.payment.entity;

import org.hibernate.annotations.Comment;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;

@Comment("결제방식 - 납부자결제")
@Entity
@Table(name = "BUYER_PAYMENT")
@DiscriminatorValue(PaymentType.Values.BUYER)
@Getter
public class BuyerPayment extends Payment {

	@Comment("납부자 결제 계좌 가능여부")
	@Column(name = "BUYER_PAYMENT_ACCOUNT_ENABLED", nullable = false)
	private boolean accountEnabled;

	@Comment("납부자 결제 CARD 가능여부")
	@Column(name = "BUYER_PAYMENT_CARD_ENABLED", nullable = false)
	private boolean cardEnabled;
}
