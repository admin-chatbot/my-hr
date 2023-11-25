package com.hr.company.model;


import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "ATTENDANCE")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private Long employeeId;

    @Column(nullable = false)
    private Date _date;

    @Column(nullable = false)
    private Boolean isPresent;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Attendance that = (Attendance) o;
        return Objects.equals(id, that.id) && Objects.equals(employeeId, that.employeeId) && Objects.equals(_date, that._date) && Objects.equals(isPresent, that.isPresent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, employeeId, _date, isPresent);
    }
}
