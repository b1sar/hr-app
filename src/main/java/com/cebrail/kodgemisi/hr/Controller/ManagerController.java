package com.cebrail.kodgemisi.hr.Controller;

import com.cebrail.kodgemisi.hr.DTO.JobApplicationDTO;
import com.cebrail.kodgemisi.hr.DTO.mapper.DTOConverter;
import com.cebrail.kodgemisi.hr.Model.JobApplication;
import com.cebrail.kodgemisi.hr.Model.JobListing;
import com.cebrail.kodgemisi.hr.Service.JobApplicationService;
import com.cebrail.kodgemisi.hr.Service.JobListingService;
import com.cebrail.kodgemisi.hr.Service.JobListingServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/manager")
@AllArgsConstructor
public class ManagerController {
    private JobListingService jobListingService;
    private JobApplicationService jobApplicationService;

    @GetMapping("/home")
    public String getManagerHome()
    {
        return "manager/index";
    }

    @GetMapping("/listjobs")
    public String listJobs(Model model, @RequestParam Optional<Integer> requestedPage)
    {
        Pageable pageable = PageRequest.of(requestedPage.orElse(1), 2);
        Page<JobListing> jobs = jobListingService.getAllJobs(pageable);

        Integer totalPages = jobs.getTotalPages();

        List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages-1)
                .boxed()
                .collect(Collectors.toList());


        model.addAttribute("mypage", jobs);
        model.addAttribute("pageNumbers", pageNumbers);

        return "manager/listjobs";
    }
    @PostMapping("/addjob")
    public String addJob(@Valid @ModelAttribute("jobListing") JobListing jobListing, BindingResult bindingResult) {

        if(bindingResult.hasErrors())
        {
            return "manager/addjob";
        }
        jobListingService.addAJobListing(jobListing);
        return "redirect:/manager/listjobs";
    }
    @GetMapping("/addjob")
    public String getJobForm(JobListing jobListing)
    {
        return "manager/addjob";
    }

    @GetMapping("/listjobs/{id}/delete")
    public String deleteJob(@PathVariable("id") Integer id)
    {
        jobListingService.deleteJobById(id);
        return "redirect:/manager/listjobs";
    }

    @GetMapping("/listjobs/{id}/detail")
    public String getJobDetails(@PathVariable("id") Integer id, Model model)
    {
        JobListing jobListing = jobListingService.getJobListingById(id);
        model.addAttribute("job", jobListing);
        return "manager/jobdetail";
    }

    @GetMapping("/applications/all")
    public String getAllApplications(Model model, @RequestParam Optional<Integer> requestedPage) throws Exception
    {
        Pageable pageable = PageRequest.of(requestedPage.orElse(1), 2);





        /*******************/
        Page<JobApplication> pages =  jobApplicationService.findAll(pageable);

        Integer totalPages = pages.getTotalPages();

        List<JobApplication> allApplications = pages.getContent();

        List<JobApplicationDTO> allApplicationDTOs = allApplications.stream()
                .map(DTOConverter::convertToJobApplicationDTO)
                .collect(Collectors.toList());


        List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages-1)
                .boxed()
                .collect(Collectors.toList());

        model.addAttribute("pageNumbers", pageNumbers);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("pages", pages);


        model.addAttribute("allApplicationDTOs", allApplicationDTOs);


        return "manager/listallapplications";
    }

    @GetMapping("/applications/all/{id}")
    public String getApplicationByJobId(@PathVariable("id") Integer id, Model model)
    {
        List<JobApplication> allApplications = jobApplicationService.getAllJobApplicationsByJobListing_Id(id);


        model.addAttribute("allApplications", allApplications);

        return "manager/listallapplications";
    }

    @GetMapping("/applications/all/{appId}/detail")
    public String getApplicationDetail(@PathVariable("appId") Integer id, Model  model)
    {
        JobApplication jobApplication = jobApplicationService.getJobapplicationById(id);
        JobApplicationDTO jobApplicationDTO = DTOConverter.convertToJobApplicationDTO(jobApplication);

        model.addAttribute("app", jobApplicationDTO);
        return "manager/application_detail";
    }

    @GetMapping("/applications/all/{jobApplicationId}/cv")
    public ResponseEntity<ByteArrayResource> getCV(@PathVariable("jobApplicationId") Integer id)
    {
        JobApplication jobApplication = jobApplicationService.getJobapplicationById(id);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(jobApplication.getCvType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment:filename=\""+jobApplication.getCvName()+"\"")
                .body(new ByteArrayResource(jobApplication.getCvData()));
    }

}