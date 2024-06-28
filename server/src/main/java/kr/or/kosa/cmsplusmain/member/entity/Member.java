package kr.or.kosa.cmsplusmain.member.entity;

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
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import kr.or.kosa.cmsplusmain.billing.entity.InvoiceSendMethod;
import kr.or.kosa.cmsplusmain.contract.entity.Contract;
import kr.or.kosa.cmsplusmain.base.entity.BaseTimeEntity;
import kr.or.kosa.cmsplusmain.base.entity.RemovableEntity;
import kr.or.kosa.cmsplusmain.vendor.entity.Vendor;
import lombok.Getter;
import lombok.Setter;

@Comment("회원 (학원의 학생)")
@Entity
@Table(name = "MEMBER", uniqueConstraints = {
	@UniqueConstraint(name = "UNIQUE_MEMBER_EMAIL", columnNames = {"MEMBER_EMAIL"}),
	@UniqueConstraint(name = "UNIQUE_MEMBER_PHONE", columnNames = {"MEMBER_PHONE"})})
@Getter
public class Member extends BaseTimeEntity implements RemovableEntity {

	@Id
	@GeneratedValue
	@Column(name = "MEMBER_ID")
	private Long id;

	@Comment("회원의 고객")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MEMBER_VENDOR", nullable = false)
	@NotNull
	private Vendor vendor;

	/************ 기본정보 ************/

	@Comment("회원 상태")
	@Enumerated(EnumType.STRING)
	@Column(name = "MEMBER_STATUS", nullable = false)
	@NotNull
	private MemberStatus status;

	@Comment("회원 이름")
	@Column(name = "MEMBER_NAME", nullable = false, length = 40)
	@Pattern(regexp = "^[가-힣a-zA-Z]{1,40}$")
	@NotNull
	@Setter
	private String name;

	@Comment("회원 이메일")
	@Column(name = "MEMBER_EMAIL", nullable = false, length = 100)
	@Email
	@NotNull
	@Setter
	private String email;

	@Comment("회원 휴대전화")
	@Column(name = "MEMBER_PHONE", nullable = false, length = 20)
	@Pattern(regexp = "^01[0-1]-?\\d{3,4}-?\\d{4}$")
	@NotNull
	@Setter
	private String phone;

	//TODO: 다음 도로명 주소 API 보고 수정하기
	@Comment("회원 주소")
	@Column(name = "MEMBER_ADDRESS", nullable = true, length = 40)
	@Setter
	private String address;

	@Comment("회원 메모")
	@Column(name = "MEMBER_MEMO", nullable = true, length = 2000)
	@Size(max = 2000)
	@Setter
	private String memo;

	/* 학생이 학원에 등록한 날짜 (DB 생성일 X) */
	@Comment("회원 등록일")
	@Column(name = "MEMBER_ENROLL_DATE", nullable = false)
	@NotNull
	@Setter
	private LocalDate enrollDate;

	/************ 계약정보 ************/

	/* 회원이 맺은 계약 목록 */
	@OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
	private List<Contract> contracts = new ArrayList<>();

	/************ 청구정보 ************/

	@Comment("회원 청구서 발송 수단")
	@Enumerated(EnumType.STRING)
	@Column(name = "MEMBER_INVOICE_SEND_METHOD", nullable = false)
	@NotNull
	@Setter
	private InvoiceSendMethod invoiceSendMethod;

	@Comment("회원 청구서 자동 발송 여부")
	@Column(name = "MEMBER_AUTO_INVOICE_SEND", nullable = false)
	@Setter
	private boolean autoInvoiceSend;

	@Comment("회원 청구 자동 생성 여부")
	@Column(name = "MEMBER_AUTO_BILLING", nullable = false)
	@Setter
	private boolean autoBilling;

	/* 회원 삭제 */
	@Override
	public void remove() {
		this.status = MemberStatus.REMOVED;
	}
}
