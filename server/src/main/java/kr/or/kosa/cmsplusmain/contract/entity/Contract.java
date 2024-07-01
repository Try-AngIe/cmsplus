package kr.or.kosa.cmsplusmain.contract.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import kr.or.kosa.cmsplusmain.base.entity.BaseTimeEntity;
import kr.or.kosa.cmsplusmain.base.entity.RemovableEntity;
import kr.or.kosa.cmsplusmain.member.entity.Member;
import kr.or.kosa.cmsplusmain.payment.entity.Payment;
import kr.or.kosa.cmsplusmain.vendor.entity.Vendor;
import lombok.Getter;
import lombok.Setter;

@Comment("회원과 고객간의 계약 (학원 - 학생 간 계약)")
@Entity
@Table(name = "CONTRACT")
@Getter
public class Contract extends BaseTimeEntity implements RemovableEntity {

	@Id
	@GeneratedValue
	@Column(name = "CONTRACT_ID")
	private Long id;

	@Comment("계약한 회원의 고객")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CONTRACT_VENDOR", nullable = false)
	@NotNull
	private Vendor vendor;

	@Comment("계약한 회원")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CONTRACT_MEMBER", nullable = false)
	@NotNull
	private Member member;

	@Comment("계약 상태")
	@Column(name = "CONTRACT_STATUS", nullable = false)
	@Enumerated(EnumType.STRING)
	@NotNull
	private ContractStatus status;

	@Comment("계약 이름")
	@Column(name = "CONTRACT_NAME", nullable = false, length = 40)
	@Pattern(regexp = "^[가-힣a-zA-Z]{1,40}$")
	@NotNull
	@Setter
	private String name;

	@Comment("계약 약정일")
	@Column(name = "CONTRACT_DATE", nullable = false)
	@NotNull
	@Setter
	private LocalDate contractDate;

	@Comment("계약 결제정보")
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CONTRACT_PAYMENT", nullable = false)
	@NotNull
	private Payment payment;

	@Comment("계약 기간 - 시작일")
	@Column(name = "CONTRACT_START_DATE", nullable = false)
	@NotNull
	private LocalDate contractStartDate;

	@Comment("계약 기간 - 종료일")
	@Column(name = "CONTRACT_END_DATE", nullable = false)
	@NotNull
	private LocalDate contractEndDate;

	/* 계약한 상품 목록 */
	@OneToMany(mappedBy = "contract", fetch = FetchType.LAZY)
	private List<ContractProduct> contractProducts = new ArrayList<>();

	/* 계약 삭제 */
	@Override
	public void remove() {
		this.status = ContractStatus.REMOVED;
	}
}
