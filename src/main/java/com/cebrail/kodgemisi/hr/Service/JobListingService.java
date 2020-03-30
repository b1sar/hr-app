package com.cebrail.kodgemisi.hr.Service;

import com.cebrail.kodgemisi.hr.Model.JobListing;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface JobListingService {
    public List<JobListing> getAllJobs();
    public Page<JobListing> getAllJobs(Pageable pageable);
    public JobListing addAJobListing(JobListing jobListing);
    public JobListing getJobListingById(Integer id);
    public void deleteJobById(Integer id);
}
