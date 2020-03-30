package com.cebrail.kodgemisi.hr.Service;

import com.cebrail.kodgemisi.hr.DAO.JobListingRepo;
import com.cebrail.kodgemisi.hr.Model.JobListing;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
/*insert into job_listing(id, job_description, job_title, last_application_date, number_of_people_to_hire) values(1, 'lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum', 'Job Title', '2019-11-14', 4);*/
@Service
@AllArgsConstructor
public class JobListingServiceImpl implements JobListingService {
    private JobListingRepo jobListingRepo;

    @Override
    public List<JobListing> getAllJobs()
    {
        return jobListingRepo.findAll();
    }

    @Override
    public Page<JobListing> getAllJobs(Pageable pageable) {

        return jobListingRepo.findAll(pageable);
    }


    @Override
    public JobListing addAJobListing(JobListing jobListing)
    {
        return jobListingRepo.save(jobListing);
    }

    @Override
    public JobListing getJobListingById(Integer id)
    {
        return jobListingRepo.getOne(id);
    }

    @Override
    public void deleteJobById(Integer id)
    {
        jobListingRepo.deleteById(id);
    }
}
