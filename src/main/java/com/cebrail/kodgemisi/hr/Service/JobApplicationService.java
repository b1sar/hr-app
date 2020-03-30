package com.cebrail.kodgemisi.hr.Service;

import com.cebrail.kodgemisi.hr.Model.JobApplication;

import java.util.List;

public interface JobApplicationService {

    public JobApplication saveJobApplication(JobApplication jobApplication);
    public JobApplication getJobapplicationById(Integer id);
    public List<JobApplication> getAllJobApplicationsByJobListing_Id(Integer id);
    public List<JobApplication> findAll();
}
