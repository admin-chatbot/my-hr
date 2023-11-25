package com.hr.company.model;


import io.swagger.v3.oas.models.security.SecurityScheme;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Entity
@Table(name = "LEAVE")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class Leave implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private Integer totalLeave;

    private Integer availedLeave;

    @Column(nullable = false)
    private String leaveYear;

    @Transient
    private Map<String, Integer> typeOfLeaves;

    @Transient
    private List<LeaveDetails> leaveDetails;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Leave leave = (Leave) o;
        return Objects.equals(id, leave.id) && Objects.equals(code, leave.code) && Objects.equals(totalLeave, leave.totalLeave) && Objects.equals(availedLeave, leave.availedLeave) && Objects.equals(leaveYear, leave.leaveYear) && Objects.equals(typeOfLeaves, leave.typeOfLeaves);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code, totalLeave, availedLeave, leaveYear, typeOfLeaves);
    }
}
