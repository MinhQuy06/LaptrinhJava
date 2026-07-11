package vn.edu.gdu.ch4labspringjpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.gdu.ch4labspringjpa.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
