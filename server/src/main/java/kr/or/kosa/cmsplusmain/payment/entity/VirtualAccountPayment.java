package kr.or.kosa.cmsplusmain.payment.entity;

import org.hibernate.annotations.Comment;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Comment("결제방식 - 가상계좌")
@Entity
@Table(name = "VIRTUAL_PAYMENT")
@DiscriminatorValue(PaymentType.Values.VIRTUAL_ACCOUNT)
@Getter
public class VirtualAccountPayment extends Payment {

	@Comment("가상계좌 은행명")
	@Column(name = "VIRTUAL_PAYMENT_BANK_NAME", nullable = false, length = 20)
	@NotNull
	private Bank bank;

	@Comment("가상계좌 계좌번호")
	@Column(name = "VIRTUAL_PAYMENT_ACCOUNT_NUMBER", nullable = false, length = 14)
	@NotBlank
	private String accountNumber;

	@Comment("가상계좌 소유자명")
	@Column(name = "VIRTUAL_PAYMENT_ACCOUNT_OWNER", nullable = false, length = 40)
	@Pattern(regexp = "^[가-힣a-zA-Z]{1,40}$")
	@NotNull
	private String accountOwner;
}
