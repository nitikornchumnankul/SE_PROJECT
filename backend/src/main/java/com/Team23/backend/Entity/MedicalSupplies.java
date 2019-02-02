package com.Team23.backend.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data

public class MedicalSupplies {
    @Id  
    @GeneratedValue(strategy= GenerationType.IDENTITY) 
    private Long medicalsuppliesId;
    private String medicalsuppliesName;

    @ManyToOne()
    @JoinColumn(name="medicalInstrumentId")
    private MedicalInstrument medicalInstrument;

    @ManyToOne()
    @JoinColumn(name="useabilityId")
    private Useability useability;

    public MedicalSupplies(){}
    public Long findById(long medicalsuppliesId){
        return this.medicalsuppliesId=medicalsuppliesId;
    }
    public String deleteByMedicalsuppliesName(String medicalsuppliesName){
        return this.medicalsuppliesName=medicalsuppliesName;
    }
}