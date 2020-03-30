package com.cebrail.kodgemisi.hr.DAO;


import com.cebrail.kodgemisi.hr.Model.JobListing;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface JobListingRepo extends JpaRepository<JobListing, Integer> {
}
