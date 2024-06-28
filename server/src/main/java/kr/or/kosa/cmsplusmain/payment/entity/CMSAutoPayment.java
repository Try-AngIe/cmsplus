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

@Comment("결제방식 - 자동결제 + 결제수단 - 실시간CMS")
@Entity
@Table(name = "CMS_AUTO_PAYMENT")
@DiscriminatorValue(PaymentMethod.Values.CMS)
@Getter
public class CMSAutoPayment extends AutoPayment {

	@Comment("CMS 계좌 은행명")
	@Column(name = "CMS_AUTO_PAYMENT_BANK_NAME", nullable = false, length = 20)
	@NotNull
	private Bank bank;

	@Column(name = "CMS_AUTO_PAYMENT_ACCOUNT_OWNER", nullable = false, length = 40)
	@Pattern(regexp = "^[가-힣a-zA-Z]{1,40}$")
	@NotNull
	private String accountOwner;

	@Column(name = "CMS_AUTO_PAYMENT_OWNER_BIRTHDATE")
	private LocalDate ownerBirthdate;
}
