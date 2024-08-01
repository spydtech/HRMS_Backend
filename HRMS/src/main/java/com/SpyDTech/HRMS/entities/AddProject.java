package com.SpyDTech.HRMS.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.engine.spi.CascadeStyle;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Project")
public class AddProject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String projectName;
    private String clientName;
    private LocalDate startDate;
    private LocalDate endDate;
    @Enumerated(EnumType.STRING)
    private ProjectProirity projectProirity;
    private String rateInDollar;
    @Enumerated(EnumType.STRING)
    private RateType rateType;
    @Enumerated(EnumType.STRING)
    private TeamLead teamLead;
    @Enumerated(EnumType.STRING)
    private Team team;
    @Lob
    private byte[] image;
    private String projectDescription;
    @Enumerated(EnumType.STRING)
    private LeaveStatus status;
    @OneToOne(cascade =CascadeType.ALL)
    @JoinColumn
    private ProjectDetail action;
    private String progress;

    public ProjectDetail getAction() {
        return action;
    }

    public void setAction(ProjectDetail action) {
        this.action = action;
    }
}
