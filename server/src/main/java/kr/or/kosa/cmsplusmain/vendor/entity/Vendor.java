package kr.or.kosa.cmsplusmain.vendor.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.Comment;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import kr.or.kosa.cmsplusmain.contract.entity.Contract;
import kr.or.kosa.cmsplusmain.base.entity.BaseTimeEntity;
import kr.or.kosa.cmsplusmain.member.entity.Member;
import kr.or.kosa.cmsplusmain.payment.entity.Payment;
import kr.or.kosa.cmsplusmain.product.entity.Product;
import lombok.Getter;

@Comment("고객 (학원의 원장)")
@Entity
@Table(name = "VENDOR", uniqueConstraints = {
	@UniqueConstraint(name = "UNIQUE_VENDOR_USERNAME", columnNames = {"VENDOR_USERNAME"}),
	@UniqueConstraint(name = "UNIQUE_VENDOR_EMAIL", columnNames = {"VENDOR_EMAIL"}),
	@UniqueConstraint(name = "UNIQUE_VENDOR_PHONE", columnNames = {"VENDOR_PHONE"})
})
@Getter
public class Vendor extends BaseTimeEntity {

	@Id
	@GeneratedValue
	@Column(name = "VENDOR_ID")
	private Long id;

	/************ 기본정보 ************/

	@Comment("고객 로그인 아이디")
	@Column(name = "VENDOR_USERNAME", nullable = false, length = 20)
	@Pattern(regexp = "^[a-z0-9]{5,20}$")
	@NotNull
	private String username;

	@Comment("고객 로그인 비밀번호")
	@Column(name = "VENDOR_PASSWORD", nullable = false, length = 16)
	//    비밀번호는 암호화된 값이 들어가기 떄문에 정규식 없음.
	//    TODO: 암호화시 저장되는 문자열의 길이 변경이 있을까??
	//    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*])[A-Za-z\\d!@#$%^&*]{8,16}$")
	@NotNull
	private String password;

	@Comment("고객 이름")
	@Column(name = "VENDOR_NAME", nullable = false, length = 40)
	@Pattern(regexp = "^[가-힣a-zA-Z]{1,40}$")
	@NotNull
	private String name;

	@Comment("고객 이메일")
	@Column(name = "VENDOR_EMAIL", nullable = false, length = 100)
	@Email
	@NotNull
	private String email;

	@Comment("고객 휴대전화")
	@Column(name = "VENDOR_PHONE", nullable = false, length = 20)
	@Pattern(regexp = "^01[0-1]-?\\d{3,4}-?\\d{4}$")
	@NotNull
	private String phone;

	@Comment("고객 유선전화")
	@Column(name = "VENDOR_HOMEPHONE", nullable = true, length = 20)
	@Pattern(regexp = "^\\d{2,3}-?\\d{3,4}-?\\d{4}$")
	private String homePhone;

	@Comment("고객 부서명")
	@Column(name = "VENDOR_DEPT", nullable = false, length = 40)
	@NotBlank
	private String department;

	/************ 회원 ************/

	/*고객이 등록한 회원목록*/
	@OneToMany(mappedBy = "vendor", fetch = FetchType.LAZY)
	private List<Member> members = new ArrayList<>();

	/************ 계약 ************/

	/*고객의 회원이 체결한 계약목록*/
	@OneToMany(mappedBy = "vendor", fetch = FetchType.LAZY)
	private List<Contract> contracts = new ArrayList<>();

	/************ 상품 ************/

	/*고객이 등록한 상품목록*/
	@OneToMany(mappedBy = "vendor", fetch = FetchType.LAZY)
	private List<Product> products = new ArrayList<>();
}
