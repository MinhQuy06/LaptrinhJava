package vn.edu.gdu.ch4labspringjpa.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.gdu.ch4labspringjpa.entity.Course;
import vn.edu.gdu.ch4labspringjpa.entity.Student;
import vn.edu.gdu.ch4labspringjpa.repository.CourseRepository;
import vn.edu.gdu.ch4labspringjpa.repository.StudentRepository;

import java.util.List;
import java.util.Optional;

@RestController // Định nghĩa REST Controller [cite: 346]
@RequestMapping("/api/students") // Đường dẫn gốc cho các API của Student
public class StudentController {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    // Constructor Injection kết nối tự động [cite: 347]
    public StudentController(StudentRepository studentRepository,
                             CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }
    // 1. API Lấy danh sách toàn bộ sinh viên (GET)
    @GetMapping
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // 2. API Lấy thông tin sinh viên theo ID (GET) [cite: 349]
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        Optional<Student> student = studentRepository.findById(id);
        return student.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build()); // Trả về 404 nếu không tìm thấy [cite: 350]
    }

    // 3. API Thêm mới một sinh viên (POST) [cite: 350]
    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student savedStudent = studentRepository.save(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedStudent); // Trả về 201 Created [cite: 351]
    }

    // 4. API Cập nhật thông tin sinh viên (PUT) [cite: 351]
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student studentDetails) {
        Optional<Student> existingStudent = studentRepository.findById(id);
        if (existingStudent.isPresent()) {
            Student student = existingStudent.get();
            student.setName(studentDetails.getName());
            student.setFull_name(studentDetails.getFull_name());
            student.setEmail(studentDetails.getEmail());
            student.setGpa(studentDetails.getGpa());
            student.setEnrollmentDate(studentDetails.getEnrollmentDate());

            Student updatedStudent = studentRepository.save(student);
            return ResponseEntity.ok(updatedStudent); // Trả về 200 OK [cite: 352]
        }
        return ResponseEntity.notFound().build();
    }

    // 5. API Xóa một sinh viên theo ID (DELETE) [cite: 353]
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            return ResponseEntity.noContent().build(); // Trả về 204 No Content [cite: 354]
        }
        return ResponseEntity.notFound().build();
    }

    // Đăng ký môn học
    @PostMapping("/{studentId}/enroll/{courseId}")
    public ResponseEntity<String> enrollStudent(
            @PathVariable Long studentId,
            @PathVariable Long courseId) {

        Optional<Student> studentOpt = studentRepository.findById(studentId);
        Optional<Course> courseOpt = courseRepository.findById(courseId);

        if (studentOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Không tìm thấy sinh viên");
        }

        if (courseOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Không tìm thấy khóa học");
        }

        Student student = studentOpt.get();
        Course course = courseOpt.get();

        student.enrollInCourse(course);

        studentRepository.save(student);

        return ResponseEntity.ok("Đăng ký môn học thành công");
    }
}
