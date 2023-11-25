package com.hr.company.model;


import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "LEAVEDETAIL")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class LeaveDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private Long leaveId;

    @Column(nullable = false)
    private String empCode;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private Date startDate;

    @Column(nullable = false)
    private Date endDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LeaveDetails that = (LeaveDetails) o;
        return Objects.equals(id, that.id) && Objects.equals(leaveId, that.leaveId)
                && Objects.equals(type, that.type)
                && Objects.equals(startDate, that.startDate)
                && Objects.equals(endDate, that.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, leaveId, type, startDate, endDate);
    }
}
