package com.devsuperior.dsmeta.controllers;

import com.devsuperior.dsmeta.dto.SallesReportDTO;
import com.devsuperior.dsmeta.dto.SallesSumaryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.services.SaleService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

	@Autowired
	private SaleService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<SaleMinDTO> findById(@PathVariable Long id) {
		SaleMinDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}

    @GetMapping(value = "/summary")
    public ResponseEntity<Page<SallesSumaryDTO>> getSummary(
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate minDate,

            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate maxDate,

            @RequestParam(required = false, defaultValue = "") String name,

            Pageable pageable) {

        LocalDate today = LocalDate.now();

        LocalDate effectiveMinDate = (minDate == null) ? today.minusYears(1) : minDate;
        LocalDate effectiveMaxDate = (maxDate == null) ? today : maxDate;

        Page<SallesSumaryDTO> result = service.SallesSumary(effectiveMinDate, effectiveMaxDate, name, pageable);

        return ResponseEntity.ok(result);
    }


    @GetMapping(value = "/report")
	public ResponseEntity<?> getReport(
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate minDate,

            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate maxDate,

            @RequestParam(required = false, defaultValue = "") String name)  {

        LocalDate today = LocalDate.now();

        LocalDate effectiveMinDate = (minDate == null) ? today.minusYears(1) : minDate;
        LocalDate effectiveMaxDate = (maxDate == null) ? today : maxDate;

        List<SallesReportDTO> result = service.sallesReport(effectiveMinDate, effectiveMaxDate, name);

        return ResponseEntity.ok(result);
    }
}
