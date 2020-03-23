package com.cebrail.kodgemisi.hr.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @NotNull
    private String thoughtsOnJob;

    /*CV start*/
    private String cvName;
    private String cvType;
    @Lob
    private byte[] cvData;
    /*CV end  */

    @ManyToOne(targetEntity = JobListing.class, cascade = CascadeType.ALL )
    private JobListing jobListing;
}
