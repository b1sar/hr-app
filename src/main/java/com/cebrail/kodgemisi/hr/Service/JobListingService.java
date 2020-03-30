package com.cebrail.kodgemisi.hr.Service;

import com.cebrail.kodgemisi.hr.Model.JobListing;

import java.util.List;

public interface JobListingService {
    public List<JobListing> getAllJobs();
    public JobListing addAJobListing(JobListing jobListing);
    public JobListing getJobListingById(Integer id);
    public void deleteJobById(Integer id);
}
