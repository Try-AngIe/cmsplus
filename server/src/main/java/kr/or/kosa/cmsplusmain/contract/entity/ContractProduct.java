package kr.or.kosa.cmsplusmain.contract.entity;

import org.hibernate.annotations.Comment;

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

@Entity
@Table(name = "CONTRACT_PRODUCT")
@Getter
public class ContractProduct {

	@Id
	@GeneratedValue
	@Column(name = "CONTRACT_PRODUCT_ID")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CONTRACT_ID", nullable = false)
	private Contract contract;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PRODUCT_ID", nullable = false)
	private Product product;

	@Comment("계약_상품 가격")
	@Column(name = "CONTRACT_PRODUCT_PRICE", nullable = false)
	private int price;

	@Comment("계약_상품 수량")
	@Column(name = "CONTRACT_PRODUCT_QUANTITY", nullable = false)
	private int quantity;
}
