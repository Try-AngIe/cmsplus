package kr.or.kosa.cmsplusmain.base.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseTimeEntity {

	@Column(name = "CREATED_DATETIME")
	private LocalDateTime createdDateTime;
	@Column(name = "UPDATED_DATETIME")
	private LocalDateTime updatedDateTime;

	@PrePersist
	protected void onCreate() {
		this.createdDateTime = LocalDateTime.now();
		this.updatedDateTime = LocalDateTime.now();
	}

	@PreUpdate
	protected void onUpdate() {
		this.updatedDateTime = LocalDateTime.now();
	}
}
