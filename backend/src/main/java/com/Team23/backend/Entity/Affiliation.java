package com.Team23.backend.Entity;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.*;
import java.time.format.DateTimeFormatter;
import java.time.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.*;
import java.util.*;

@Entity  //บอกว่าเป็น class entity class ที่เก็บขอมูล
@Data  // lombox จะสร้าง method getter setter ให้เอง
@ToString
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Table(name="Affiliation") //ชื่อตาราง
public class Affiliation {
    @Id  //  Annotations  @Id  บอกว่าเป็น  Primary  key
    @SequenceGenerator(name="Affiliation_seq",sequenceName="affiliation_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="affiliation_seq")
    // Annotations Generate id เอง ตอน insert
    @Column(name="Affiliation_ID",unique=true,nullable=false)
    private Long affiliationId;

    @NotNull
    private String affiliationName;

    public void setAffiliationName(String affiliationName) {
        this.affiliationName = affiliationName;
    }
}