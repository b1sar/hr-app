package com.cebrail.kodgemisi.hr.Service;

import com.cebrail.kodgemisi.hr.DAO.JobApplicationRepo;
import com.cebrail.kodgemisi.hr.Model.JobApplication;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class JobApplicationService {
    private JobApplicationRepo jobApplicationRepo;

    public JobApplication saveJobApplication(JobApplication jobApplication)
    {
        return jobApplicationRepo.save(jobApplication);
    }

    public JobApplication getJobapplicationById(Integer id)
    {
        return jobApplicationRepo.getOne(id);
    }

    public List<JobApplication> getAllJobApplicationsByJobListing_Id(Integer id)
    {
        return jobApplicationRepo.getAllByJobListing_Id(id);
    }

    public List<JobApplication> findAll() {
        return jobApplicationRepo.findAll();
    }

}