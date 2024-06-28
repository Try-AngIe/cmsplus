package kr.or.kosa.cmsplusmain.product.entity;

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
import kr.or.kosa.cmsplusmain.base.entity.BaseTimeEntity;
import kr.or.kosa.cmsplusmain.base.entity.RemovableEntity;
import kr.or.kosa.cmsplusmain.vendor.entity.Vendor;
import lombok.Getter;
import lombok.Setter;

@Comment("고객이 등록하는 상품")
@Entity
@Table(name = "PRODUCT")
@Getter
public class Product extends BaseTimeEntity implements RemovableEntity {

	@Id
	@GeneratedValue
	@Column(name = "PRODUCT_ID")
	private Long id;

	@Comment("상품 고객")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PRODUCT_VENDOR_ID", nullable = false)
	@NotNull
	private Vendor vendor;

	@Comment("상품 상태")
	@Enumerated(EnumType.STRING)
	@Column(name = "PRODUCT_STATUS", nullable = false)
	@NotNull
	private ProductStatus status;

	@Comment("상품 이름")
	@Column(name = "PRODUCT_NAME", nullable = false, length = 20)
	@Size(min = 1, max = 20)
	@NotNull
	private String name;

	@Comment("상품 금액")
	@Column(name = "PRODUCT_PRICE", nullable = false)
	@Setter
	private int price;

	@Comment("상품 비고")
	@Column(name = "PRODUCT_MEMO", nullable = true, length = 2000)
	@Size(max = 2000)
	@Setter
	private String memo;

	@Override
	public void remove() {
		this.status = ProductStatus.REMOVED;
	}
}
