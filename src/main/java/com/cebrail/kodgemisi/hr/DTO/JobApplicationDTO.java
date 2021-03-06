package com.cebrail.kodgemisi.hr.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class JobApplicationDTO {
    private Integer id;

    @NotNull
    private String name;

    @NotNull
    @Email(message = "Lutfen uygun bir email giriniz.")
    private String email;

    @NotNull
    private String phone;

    @NotNull
    private String address;

    //TODO:Should accept length of not more than what db accepts
    @NotNull
    private String thoughtsOnJob;

    //@NotNull
    //TOdO: Should accept only doc or pdf file formats
    private MultipartFile cv;
}