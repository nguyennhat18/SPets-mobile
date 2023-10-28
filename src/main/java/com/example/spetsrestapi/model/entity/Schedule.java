package com.example.spetsrestapi.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "schedule")
public class Schedule extends BaseEntity implements Serializable {

    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "activity_date")
    private Date activityDate;

    @Column(name = "activity_type")
    private String activityType;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "start_time")
    private String startTime;

    @Column(name = "end_time")
    private String endTime;

    @Column(name = "repeat_interval")
    private int repeatInterval;

    @Column(name = "status")
    private boolean status;

}
