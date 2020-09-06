package com.cebrail.kodgemisi.hr.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    private String name;

    
    private String email;


    private String phone;

    private String address;

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
