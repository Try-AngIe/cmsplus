package kr.or.kosa.cmsplusmain.vendor.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class VendorDto {

	/* PK */
	private final Long id;

	/************ 기본정보 ************/

	/* 고객 로그인 아이디 */
	@Pattern(regexp = "^[a-z0-9]{5,20}$")
	@NotNull
	private String username;

	/* 고객 이름 */
	@Pattern(regexp = "^[가-힣a-zA-Z]{1,40}$")
	@NotNull
	private String name;

	/* 고객 이메일 */
	@Email
	@NotNull
	private String email;

	/* 고객 휴대전화 */
	@Pattern(regexp = "^01[0-1]-?\\d{3,4}-?\\d{4}$")
	@NotNull
	private String phone;

	/* 고객 유선전화 */
	@Pattern(regexp = "^\\d{2,3}-?\\d{3,4}-?\\d{4}$")
	private String homePhone;

	/* 고객 부서명 */
	@NotBlank
	private String department;
}
