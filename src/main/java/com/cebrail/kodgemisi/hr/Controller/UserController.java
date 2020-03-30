package com.cebrail.kodgemisi.hr.Controller;

import com.cebrail.kodgemisi.hr.DTO.JobApplicationDTO;
import com.cebrail.kodgemisi.hr.DTO.mapper.DTOConverter;
import com.cebrail.kodgemisi.hr.Model.JobApplication;
import com.cebrail.kodgemisi.hr.Model.JobListing;
import com.cebrail.kodgemisi.hr.Service.JobApplicationService;
import com.cebrail.kodgemisi.hr.Service.JobApplicationServiceImpl;
import com.cebrail.kodgemisi.hr.Service.JobListingService;
import com.cebrail.kodgemisi.hr.Service.JobListingServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller()
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    private JobApplicationService jobApplicationService;
    private JobListingService jobListingService;

    @GetMapping("/home")
    public String getUserHome()
    {
        return "user/index";
    }

    @GetMapping("/listjobs")
    public String listJobs(Model model)
    {
        List<JobListing> jobs = jobListingService.getAllJobs();
        model.addAttribute("jobs", jobs);
        return "user/listjobs";
    }

    /*DETAİLS*/
    @GetMapping("/listjobs/{jobId}/jobdetail")
    public String getJobDetails(@PathVariable("jobId") Integer id, Model model)
    {
        JobListing jobListing = jobListingService.getJobListingById(id);
        model.addAttribute("job", jobListing);
        return "user/jobdetail";
    }
    /*END DETAILS*/


    @PostMapping(value = "/listjobs/{jobId}/apply")
    public String apply(@PathVariable("jobId") Integer jobId,
                        @Valid @ModelAttribute("jobApplicationDTO") JobApplicationDTO jobApplicationDTO,
                        BindingResult bindingResult) throws IOException {
        if(bindingResult.hasErrors())
        {
            return "user/apply";
        }

        JobListing temp = jobListingService.getJobListingById(jobId);

        JobApplication jobApplication = DTOConverter.convertToJobApplication(jobApplicationDTO);
        jobApplication.setJobListing(temp);

        jobApplicationService.saveJobApplication(jobApplication);
        return "user/applicationSuccess";
    }

    @GetMapping("/listjobs/{jobId}/apply")
    public String getJobApplicationPage(@PathVariable("jobId") Integer jobId, JobApplicationDTO jobApplicationDTO, Model model)
    {
        model.addAttribute("jobApplicationDTO", jobApplicationDTO);
        model.addAttribute("jobId", jobId);
        return "user/apply";
    }
}