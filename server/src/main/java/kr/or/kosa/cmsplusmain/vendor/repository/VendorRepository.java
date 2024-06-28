package kr.or.kosa.cmsplusmain.vendor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.or.kosa.cmsplusmain.vendor.entity.Vendor;

public interface VendorRepository extends JpaRepository<Vendor, Long> {
}
