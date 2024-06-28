package kr.or.kosa.cmsplusmain.vendor.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.kosa.cmsplusmain.vendor.dto.VendorDto;
import kr.or.kosa.cmsplusmain.vendor.service.VendorService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/vendor")
@RequiredArgsConstructor
public class VendorController {

	private final VendorService vendorService;

	@GetMapping
	public List<VendorDto> getAllVendors() {
		return vendorService.findAll();
	}
}
