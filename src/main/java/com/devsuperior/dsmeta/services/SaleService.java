package com.devsuperior.dsmeta.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.devsuperior.dsmeta.dto.SallesReportDTO;
import com.devsuperior.dsmeta.dto.SallesSumaryDTO;
import com.devsuperior.dsmeta.projection.SallesMinProjection;
import com.devsuperior.dsmeta.projection.SallesReportProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;
	
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

    public Page<SallesSumaryDTO> SallesSumary(LocalDate minDate, LocalDate maxDate, String name, Pageable pageable) {

        System.out.println("minDate: " + minDate);
        System.out.println("maxDate: " + maxDate);
        System.out.println("name: " + name);

        Page<SallesMinProjection> projection = repository.SallesByPeriodOfSeller(minDate, maxDate, name, pageable);

        Page<SallesSumaryDTO> dtoPage = projection.map(SallesSumaryDTO::new);

        return dtoPage;
    }


    public List<SallesReportDTO> sallesReport(LocalDate minDate, LocalDate maxDate, String name){

        System.out.println("minDate" + minDate);
        System.out.println("maxDate" + maxDate);
        System.out.println("name" + name);

        List<SallesReportProjection> projection = repository.sallesReport(minDate, maxDate, name);

        List<SallesReportDTO> listDTO = new ArrayList<>();

        for (SallesReportProjection proj : projection){
            listDTO.add(new SallesReportDTO(proj));
        }
        return listDTO;
    }

}
