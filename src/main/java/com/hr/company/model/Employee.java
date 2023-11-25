package com.hr.company.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;


@Entity
@Table(name = "EMPLOYEE")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false,unique = true)
    private String code;

    @Column(nullable = false)
    private String name;

    private String address;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id) && Objects.equals(code, employee.code) && Objects.equals(name, employee.name) && Objects.equals(address, employee.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code, name, address);
    }
}
