package com.cebrail.kodgemisi.hr.DTO.mapper;

import com.cebrail.kodgemisi.hr.DTO.JobApplicationDTO;
import com.cebrail.kodgemisi.hr.Model.CV;
import com.cebrail.kodgemisi.hr.Model.JobApplication;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class DTOConverter {
    public static JobApplication convertToJobApplication(JobApplicationDTO jobApplicationDTO) throws IOException {
        JobApplication jobApplication = new JobApplication();


        jobApplication.setName(jobApplicationDTO.getName());
        jobApplication.setEmail(jobApplicationDTO.getEmail());
        jobApplication.setPhone(jobApplicationDTO.getPhone());
        jobApplication.setAddress(jobApplicationDTO.getAddress());
        jobApplication.setThoughtsOnJob(jobApplicationDTO.getThoughtsOnJob());

        //Convert CV
        jobApplication.setCvName(jobApplicationDTO.getCv().getOriginalFilename());
        jobApplication.setCvType(jobApplicationDTO.getCv().getContentType());
        jobApplication.setCvData(jobApplicationDTO.getCv().getBytes());

        return jobApplication;
    }

    public static JobApplicationDTO convertToJobApplicationDTO(JobApplication jobApplication)
    {
        JobApplicationDTO jobApplicationDTO = new JobApplicationDTO();

        jobApplicationDTO.setId(jobApplication.getId());
        jobApplicationDTO.setName(jobApplication.getName());
        jobApplicationDTO.setEmail(jobApplication.getEmail());
        jobApplicationDTO.setPhone(jobApplication.getPhone());
        jobApplicationDTO.setAddress(jobApplication.getAddress());
        jobApplicationDTO.setThoughtsOnJob(jobApplication.getThoughtsOnJob());

        //Convert CV

        MultipartFile multipartFile = new CV(jobApplication.getCvName(), jobApplication.getCvType(), jobApplication.getCvData());
        //

        jobApplicationDTO.setCv(multipartFile);

        return  jobApplicationDTO;

    }
}