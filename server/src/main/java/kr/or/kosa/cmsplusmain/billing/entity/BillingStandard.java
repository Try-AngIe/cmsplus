package kr.or.kosa.cmsplusmain.billing.entity;

import java.time.LocalDate;

import org.hibernate.annotations.Comment;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import kr.or.kosa.cmsplusmain.contract.entity.Contract;
import kr.or.kosa.cmsplusmain.base.entity.BaseTimeEntity;
import kr.or.kosa.cmsplusmain.base.entity.RemovableEntity;
import kr.or.kosa.cmsplusmain.member.entity.Member;
import lombok.Getter;

@Comment("청구 생성 정보 (정기적으로 혹은 추가 청구를 만들기 위한 정보)")
@Entity
@Table(name = "BILLING_STANDARD")
@Getter
public class BillingStandard extends BaseTimeEntity implements RemovableEntity {

	@Id
	@GeneratedValue
	@Column(name = "BILLING_STANDARD_ID")
	private Long id;

	@Comment("청구한 회원")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BILLING_STANDARD_MEMBER", nullable = false)
	@NotNull
	private Member member;

	@Comment("청구 설정 상태")
	@Enumerated(EnumType.STRING)
	@Column(name = "BILLING_STANDARD_STATUS", nullable = false)
	@NotNull
	private BillingStandardStatus status;

	@Comment("청구 타입 [정기 | 추가]")
	@Enumerated(EnumType.STRING)
	@Column(name = "BILLING_STANDARD_TYPE", nullable = false)
	@NotNull
	private BillingType type;

	@Comment("청구 생성 기반이된 계약")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BILLING_STANDARD_CONTRACT", nullable = false)
	@NotNull
	private Contract contract;

	@Comment("청구의 약정일")
	@Column(name = "BILLING_STANDARD_CONTRACT_DATE", nullable = false)
	private LocalDate contractDate;

	@Comment("청구월")
	@Column(name = "BILLING_STANDARD_MONTH", nullable = false, columnDefinition = "TINYINT")
	@Size(min = 1, max = 12)
	private int billingMonth;

	@Comment("청구 기간 - 시작일")
	@Column(name = "BILLING_STANDARD_START_DATE", nullable = false)
	@NotNull
	private LocalDate billingStartDate;

	@Comment("청구 기간 - 종료일")
	@Column(name = "BILLING_STANDARD_END_DATE", nullable = false)
	@NotNull
	private LocalDate billingEndDate;

	/* 청구 설정 삭제 */
	@Override
	public void remove() {
		this.status = BillingStandardStatus.REMOVED;
	}
}
