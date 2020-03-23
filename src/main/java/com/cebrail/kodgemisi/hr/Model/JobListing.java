package com.cebrail.kodgemisi.hr.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class JobListing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Bos birakilamaz")
    @Size(min = 10, max = 500, message = "10 ile 500 karakter arasinda olmali")
    private String jobTitle;

    @NotEmpty(message = "Bos birakilamaz")
    @Size(max = 3000, message = "3000 karakterden fazla olamaz")
    private String jobDescription;

    @NotNull(message = "Bos birakilamaz")
    @Min(message = "En az bir kisi olmali", value = 1)
    private Integer numberOfPeopleToHire;

    @NotNull(message = "Bos birakilamaz")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate lastApplicationDate;
}