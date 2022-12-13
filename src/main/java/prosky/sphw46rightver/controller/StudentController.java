package prosky.sphw46rightver.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import prosky.sphw46rightver.entity.Faculty;
import prosky.sphw46rightver.entity.Student;
import prosky.sphw46rightver.service.StudentService;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping(path = "student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping  // POST http:localhost:8080/student
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student newStudent = studentService.createStudent(student);
        if (newStudent == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(newStudent);
    }

    @GetMapping("{id}")  // GET http:localhost:8080/student/1
    public ResponseEntity<Student> getStudent(@PathVariable Long id) {
        Student student = studentService.getStudent(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @PutMapping   // PUT http:localhost:8080/student
    public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
        Student newStudent = studentService.updateStudent(student);
        if (newStudent == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(newStudent);
    }

    @DeleteMapping(path = "{id}")   // DELETE http:localhost:8080/student/1
    public ResponseEntity deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/getAll")   // GET http:localhost:8080/student/getAll
    public ResponseEntity<Collection<Student>> getAll() {
        Collection<Student> students = studentService.getAll();
        if (students == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(students);
    }

    @GetMapping   // GET http:localhost:8080/student
    public ResponseEntity<Collection<Student>> sortStudentsByAge(@RequestParam int min,
                                                                 @RequestParam int max) {
        return ResponseEntity.ok(studentService.findStudentsByAgeDiapason(min, max));
    }

    @GetMapping("/faculty")
    public ResponseEntity<Collection<Student>> findStudentsByFaculty(@RequestParam long faciltyId) {
        return ResponseEntity.ok(studentService.findStudentsByFaculty(faciltyId));
    }

    @GetMapping("/facultyStudent")
    public ResponseEntity<Faculty> findFacultyByStudent(@RequestParam long studentId) {
        Faculty faculty = studentService.getFacultyByStudent(studentId);
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @GetMapping("/countStudents")
    public ResponseEntity<Integer> getAmountOfStudents() {
        Integer amountOfStudents = studentService.getAmountOfStudents();
        return ResponseEntity.ok(amountOfStudents);
    }

    @GetMapping("/averageAgeOfStudents")
    public ResponseEntity<Double> getAverageAgeOfStudents() {
        Double averageAgeOfStudents = studentService.getAverageAgeOfStudents();
        return ResponseEntity.ok(averageAgeOfStudents);
    }

    @GetMapping("/lastOfStudents")
    public ResponseEntity<List<Student>> getLastFiveOfStudentsById() {
        return ResponseEntity.ok(studentService.getLastFiveOfStudentsById());
    }

    @GetMapping("/studentsStartWith")
    public ResponseEntity<List<String>> getAllStudentsStartingWithLetter(@RequestParam String letter) {
        return ResponseEntity.ok(studentService.getAllStudentsStartingWithLetter(letter));
    }

    @GetMapping("/averageAge")
    public ResponseEntity<Double> getAverageAgeOfAllStudents() {
        return ResponseEntity.ok(studentService.getAverageAgeOfAllStudents());
    }
    @GetMapping("/getAllStudentsWithThreads")
    public ResponseEntity<Void> getAllStudentsWithThreads() {
        studentService.getAllStudentsWithThreads();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getAllStudentsWithSynchronizedThreads")
    public ResponseEntity<Void> getAllStudentsWithSynchronizedThreads() {
        studentService.getAllStudentsWithSynchronizedThreads();
        return ResponseEntity.ok().build();
    }
}
