package com.cebrail.kodgemisi.hr.DAO;


import com.cebrail.kodgemisi.hr.Model.JobListing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobListingRepo extends JpaRepository<JobListing, Integer> {
}
