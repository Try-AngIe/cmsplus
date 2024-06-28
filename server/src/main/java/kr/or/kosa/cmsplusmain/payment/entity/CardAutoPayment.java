package kr.or.kosa.cmsplusmain.payment.entity;

import java.time.LocalDate;

import org.hibernate.annotations.Comment;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Comment("결제방식 - 자동결제 + 결제수단 - 카드")
@Entity
@Table(name = "CARD_AUTO_PAYMENT")
@DiscriminatorValue(PaymentMethod.Values.CARD)
@Getter
public class CardAutoPayment extends AutoPayment {

	@Column(name = "CARD_AUTO_PAYMENT_CARD_NUMBER", length = 16)
	private String cardNumber;

	@Column(name = "CARD_AUTO_PAYMENT_CARD_EXPIRE_DATE")
	private LocalDate expireDate;

	@Column(name = "CARD_AUTO_PAYMENT_CARD_OWNER", length = 40)
	@Pattern(regexp = "^[가-힣a-zA-Z]{1,40}$")
	@NotNull
	private String owner;

	@Column(name = "CARD_AUTO_PAYMENT_CARD_OWNER_BIRTH_DATE")
	private LocalDate ownerBirthDate;
}
