package com.marketplace.catalogservice.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "GENDER")
@Schema(description = "Гендер")
public class Gender {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Schema(example = "1")
    private Long id;

    @Column(name = "gender_name")
    @NotEmpty
    @Schema(example = "male")
    private String genderName;

}