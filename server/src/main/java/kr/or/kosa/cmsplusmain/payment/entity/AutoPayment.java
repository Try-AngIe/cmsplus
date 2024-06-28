package kr.or.kosa.cmsplusmain.payment.entity;

import java.time.LocalDate;

import org.hibernate.annotations.Comment;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Comment("결제방식 - 자동결제")
@Entity
@Table(name = "AUTO_PAYMENT")
@DiscriminatorValue(PaymentType.Values.AUTO)
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "AUTO_PAYMENT_METHOD")
@Getter
public class AutoPayment extends Payment {

	@Comment("자동결제 정보 회원 간편동의 서명 이미지 URL")
	@Column(name = "AUTO_PAYMENT_SIGN_IMG_URL", nullable = true, length = 2000)
	@Pattern(regexp = "^(https?://)?([\\da-z.-]+)\\.([a-z.]{2,6})([/\\w .-]*)*/?$")
	@Setter
	private String signImgUrl;

	@Comment("자동결제 정보 고객이 등록한 동의서 이미지 URL")
	@Column(name = "AUTO_PAYMENT_CONSENT_IMG_URL", nullable = true, length = 2000)
	@Pattern(regexp = "^(https?://)?([\\da-z.-]+)\\.([a-z.]{2,6})([/\\w .-]*)*/?$")
	@Setter
	private String consentImgUrl;

	@Comment("자동결제 정보 간편동의 마지막 요청일")
	@Column(name = "AUTO_PAYMENT_SIMPCONSENT_REQUSET_DATE", nullable = true)
	private LocalDate simpleConsentReqDate;

	/* 자동결제 동의 여부 */
	public boolean isConsented() {
		return (signImgUrl != null) || (consentImgUrl != null);
	}

	/* 간편동의 요청 */
	public void sendSimpConsent() {
		this.simpleConsentReqDate = LocalDate.now();
	}
}
