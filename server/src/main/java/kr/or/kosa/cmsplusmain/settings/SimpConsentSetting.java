package kr.or.kosa.cmsplusmain.settings;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import kr.or.kosa.cmsplusmain.payment.entity.Payment;
import kr.or.kosa.cmsplusmain.payment.entity.PaymentMethod;
import kr.or.kosa.cmsplusmain.product.entity.Product;
import kr.or.kosa.cmsplusmain.vendor.entity.Vendor;
import lombok.Getter;

@Entity
@Table(name = "SIMPCONSENT_SETTINGS")
@Getter
public class SimpConsentSetting {

	/* 최대 간편동의 설정 상품 수 */
	private static final int MAX_SIMP_CONSENT_PRODUCTS = 3;

	@Id @GeneratedValue
	private Long id;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SIMPCONSENT_VENDOR")
	private Vendor vendor;

	/*간편동의 설정 결제수단*/
	@ElementCollection(targetClass = PaymentMethod.class, fetch = FetchType.LAZY)
	@CollectionTable(name = "VENDOR_SIMPCONSENT_PAYMENT_METHOD")
	@Enumerated(EnumType.STRING)
	@Column(name = "SIMPCONSENT_PAYMENT_METHOD")
	private Set<PaymentMethod> simpConsentPayments = new HashSet<>();

	/*간편동의 설정 상품*/
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "SIMPCONSENT_VENDOR_PRODUCT",
		joinColumns = @JoinColumn(name = "VENDOR_ID"),
		inverseJoinColumns = @JoinColumn(name = "PRODUCT_ID")
	)
	private Set<Product> simpConsentProducts = new HashSet<>();
}
