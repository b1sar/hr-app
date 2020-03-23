package com.cebrail.kodgemisi.hr.DAO;

import com.cebrail.kodgemisi.hr.Model.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobApplicationRepo extends JpaRepository<JobApplication, Integer> {

    public List<JobApplication> getAllByJobListing_Id(Integer id);
}
