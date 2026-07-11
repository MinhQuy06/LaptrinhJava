package vn.edu.gdu.ch4labspringjpa.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "Student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "student_code", nullable = false, length = 20)
    private String name;
    @Column(name = "full_name",unique = true, nullable = false, length = 100)
    private String full_name;
    @Column(name = "email", unique = true, nullable = false, length = 150)
    private String email;
    @Column(name = "gpa", nullable = true, precision = 3, scale = 2)
    private BigDecimal gpa;
    @Column(name = "enrollment_Date")
    private LocalDate enrollment_date;

    protected Student() {
    }

    public Student(String name, String full_name, String email, BigDecimal gpa, LocalDate enrollment_date) {
        this.name = name;
        this.email = email;
        this.full_name = full_name;
        this.gpa = gpa;
        this.enrollment_date = enrollment_date;
    }

    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFull_name() {
        return  full_name;
    }

    public void setFull_name(String full_name){
        this.full_name = full_name;
    }

    public BigDecimal getGpa() {
        return gpa;
    }

    public void setGpa(BigDecimal gpa) {
        this.gpa = gpa;
    }

    public LocalDate getEnrollmentDate() {
        return enrollment_date;
    }

    public void setEnrollmentDate(LocalDate enrollmentDate) {
        this.enrollment_date = enrollmentDate;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", studentCode='" + name + '\'' +
                ", fullName='" + full_name + '\'' +
                ", email='" + email + '\'' +
                ", gpa=" + gpa +
                ", enrollmentDate=" + enrollment_date +
                '}';
    }
}
