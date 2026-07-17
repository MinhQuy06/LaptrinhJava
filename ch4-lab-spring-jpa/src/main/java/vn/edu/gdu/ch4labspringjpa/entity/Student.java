package vn.edu.gdu.ch4labspringjpa.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "student_code", nullable = false, length = 20)
    private String name;

    @Column(name = "full_name", unique = true, nullable = false, length = 100)
    private String full_name;

    @Column(name = "email", unique = true, nullable = false, length = 150)
    private String email;

    @Column(name = "gpa", precision = 3, scale = 2)
    private BigDecimal gpa;

    @Column(name = "enrollment_Date")
    private LocalDate enrollment_date;

    // ===== Quan hệ Many-To-Many =====
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "student_course",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private Set<Course> courses = new HashSet<>();

    protected Student() {
    }

    public Student(String name, String full_name, String email,
                   BigDecimal gpa, LocalDate enrollment_date) {
        this.name = name;
        this.email = email;
        this.full_name = full_name;
        this.gpa = gpa;
        this.enrollment_date = enrollment_date;
    }

    // ===== Hàm đăng ký môn học =====
    public void enrollInCourse(Course course) {
        this.courses.add(course);
        course.getStudents().add(this);
    }

    // ===== Getter & Setter =====

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
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