package kr.or.kosa.cmsplusmain.billing.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import kr.or.kosa.cmsplusmain.product.entity.Product;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "BILLING_PRODUCT")
@Getter
@Setter
public class BillingProduct {

	@Id
	@GeneratedValue
	@Column(name = "BILLING_PRODUCT_ID")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BILLING_PRODUCT_BILLING")
	private Billing billing;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BILLING_PRODUCT_PRODUCT")
	private Product product;

	@Column(name = "BILLING_PRODUCT_EXTRA_PRICE")
	private int extraPrice;

	@Column(name = "BILLING_PRODUCT_DISCOUNT_PRICE")
	private int discountPrice;

	@Column(name = "BILLING_PRODUCT_QUANTITY")
	private int quantity;

	public int getTotalPrice() {
		return (product.getPrice() + extraPrice + discountPrice) * quantity;
	}
}
