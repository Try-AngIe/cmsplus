package kr.or.kosa.cmsplusmain.vendor.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.kosa.cmsplusmain.vendor.dto.VendorDto;
import kr.or.kosa.cmsplusmain.vendor.repository.VendorRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class VendorService {

	private final VendorRepository vendorRepository;

	//TODO to Dto
	public List<VendorDto> findAll() {
		return vendorRepository.findAll().stream()
			.map(entity -> VendorDto.builder()
				.id(entity.getId())
				.username(entity.getUsername())
				.name(entity.getName())
				.email(entity.getEmail())
				.phone(entity.getPhone())
				.homePhone(entity.getHomePhone())
				.department(entity.getDepartment())
				.build())
			.toList();
	}
}
