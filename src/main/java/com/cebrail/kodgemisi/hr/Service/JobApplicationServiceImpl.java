package com.cebrail.kodgemisi.hr.Service;

import com.cebrail.kodgemisi.hr.DAO.JobApplicationRepo;
import com.cebrail.kodgemisi.hr.Model.JobApplication;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class JobApplicationServiceImpl implements JobApplicationService {
    private JobApplicationRepo jobApplicationRepo;

    @Override
    public JobApplication saveJobApplication(JobApplication jobApplication)
    {
        return jobApplicationRepo.save(jobApplication);
    }

    @Override
    public JobApplication getJobapplicationById(Integer id)
    {
        return jobApplicationRepo.getOne(id);
    }

    @Override
    public List<JobApplication> getAllJobApplicationsByJobListing_Id(Integer id)
    {
        return jobApplicationRepo.getAllByJobListing_Id(id);
    }

    @Override
    public List<JobApplication> findAll() {
        return jobApplicationRepo.findAll();
    }

    @Override
    public Page<JobApplication> findAll(Pageable pageable) {

        return jobApplicationRepo.findAll(pageable);
    }

}