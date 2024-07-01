package kr.or.kosa.cmsplusmain.billing.entity;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.annotations.Comment;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import kr.or.kosa.cmsplusmain.base.entity.BaseTimeEntity;
import kr.or.kosa.cmsplusmain.base.entity.RemovableEntity;
import lombok.Getter;
import lombok.Setter;

@Comment("청구 (매 달 새로 쌓이는 정보)")
@Entity
@Table(name = "BILLING")
@Getter
public class Billing extends BaseTimeEntity implements RemovableEntity {

	@Id
	@GeneratedValue
	@Column(name = "BILLING_ID")
	private Long id;

	@Comment("청구 기준 정보")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BILLING_STANDARD_ID")
	private BillingStandard billingStandard;

	@Comment("청구 상태")
	@Enumerated(EnumType.STRING)
	@Column(name = "BILLING_STATUS", nullable = false)
	@NotNull
	private BillingStatus status;

	@Comment("청구서 메시지")
	@Column(name = "BILLING_MEMO", nullable = true, length = 2000)
	@Setter
	private String memo;

	@Comment("수납 기간 - 시작일")
	@Column(name = "BILLING_START_DATE", nullable = false)
	@NotNull
	private LocalDate billingStartDate;

	@Comment("수납 기간 - 종료일")
	@Column(name = "BILLING_END_DATE", nullable = false)
	@NotNull
	private LocalDate billingEndDate;

	/* 청구 상품 목록 */
	@OneToMany(mappedBy = "billing", orphanRemoval = true, cascade = CascadeType.ALL)
	private List<BillingProduct> billingProducts;

	/* 청구 총 금액 */
	public int getBillingMoney() {
		return billingProducts.stream()
			.mapToInt(BillingProduct::getTotalPrice)
			.sum();
	}

	public LocalDate getPaymentDate() {
		throw new RuntimeException("Not implemented yet");
	}

	public void sendInvoice() {
		throw new RuntimeException("Not implemented yet");
	}

	public void cancelInvoice() {
		throw new RuntimeException("Not implemented yet");
	}

	public void payRealTime() {
		throw new RuntimeException("Not implemented yet");
	}

	public void cancelPay() {
		throw new RuntimeException("Not implemented yet");
	}

	@Override
	public void remove() {
		this.status = BillingStatus.REMOVED;
	}
}
