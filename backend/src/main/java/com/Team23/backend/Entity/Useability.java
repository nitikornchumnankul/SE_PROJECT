package com.Team23.backend.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode
public class Useability {
    @Id  //  Annotations  @Id  บอกว่าเป็น  Primary  key
    @GeneratedValue(strategy= GenerationType.IDENTITY) 
    private Long useabilityId;
    private String useabilityName;
}