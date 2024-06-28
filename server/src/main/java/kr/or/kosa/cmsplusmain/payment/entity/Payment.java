package kr.or.kosa.cmsplusmain.payment.entity;

import org.hibernate.annotations.Comment;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import kr.or.kosa.cmsplusmain.base.entity.BaseTimeEntity;
import kr.or.kosa.cmsplusmain.base.entity.RemovableEntity;
import lombok.Getter;

@Comment("결제정보")
@Entity
@Table(name = "PAYMENT")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "PAYMENT_TYPE")
@Getter
public abstract class Payment extends BaseTimeEntity implements RemovableEntity {

	@Id
	@GeneratedValue
	@Column(name = "PAYMENT_ID")
	private Long id;

	@Comment("결제정보 상태")
	@Column(name = "PAYMENT_STATUS", nullable = false)
	@Enumerated(EnumType.STRING)
	private PaymentStatus status;

	@Override
	public void remove() {
		this.status = PaymentStatus.REMOVED;
	}
}
