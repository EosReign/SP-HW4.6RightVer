package prosky.sphw46rightver.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import prosky.sphw46rightver.entity.Faculty;
import prosky.sphw46rightver.entity.Student;
import prosky.sphw46rightver.repository.StudentRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final FacultyService facultyService;

    private final StudentRepository studentRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(AvatarService.class);

    public StudentService(FacultyService facultyService, StudentRepository studentRepository) {
        this.facultyService = facultyService;
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {
        LOGGER.debug("Method createStudent was invoked");
        return studentRepository.save(student);
    }


    public Student getStudent(Long id) {
        LOGGER.debug("Method getStudent was invoked");
        return studentRepository.findById(id).orElse(null);
    }

    public Student updateStudent(Student student) {
        LOGGER.debug("Method updateStudent was invoked");
        return studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        LOGGER.debug("Method deleteStudent was invoked");
        studentRepository.deleteById(id);
    }

    public Collection<Student> getAll() {
        LOGGER.debug("Method getAll was invoked");
        return studentRepository.findAll();
    }

    public Collection<Student> findStudentsByAgeDiapason(int min, int max) {
        LOGGER.debug("Method findStudentsByAgeDiapason was invoked");
        return studentRepository.findByAgeBetween(min, max);
    }

    public Collection<Student> findStudentsByFaculty(long id) {
        LOGGER.debug("Method findStudentsByFaculty was invoked");
        return studentRepository.findStudentByFaculty_Id(id);
    }

    public Faculty getFacultyByStudent(long id) {
        LOGGER.debug("Method getFacultyByStudent was invoked");
        Student student = getStudent(id);
        Collection<Faculty> faculties = facultyService.getAll();
        return faculties.stream().filter(faculty -> faculty.getStudents().contains(student))
                .findFirst().orElse(null);
    }

    public Integer getAmountOfStudents() {
        LOGGER.debug("Method getAmountOfStudents was invoked");
        return studentRepository.getAmountOfStudents();
    }

    public Double getAverageAgeOfStudents() {
        LOGGER.debug("Method getAverageAgeOfStudents was invoked");
        return studentRepository.getAverageAgeOfStudents();
    }

    public List<Student> getLastFiveOfStudentsById() {
        LOGGER.debug("Method getLastFiveOfStudentsById was invoked");
        return studentRepository.getLastOfStudentsById();
    }

    public List<String> getAllStudentsStartingWithLetter(String letter) {
        LOGGER.debug("Method getAllStudentsStartingWithLetter was invoked");
        return studentRepository.findAll()
                .parallelStream()
                .filter(s -> s.getName().startsWith(letter.toUpperCase()))
                .map(s -> s.getName().toUpperCase())
                .collect(Collectors.toList());
    }


    public Double getAverageAgeOfAllStudents() {
        LOGGER.debug("Method getAverageAgeOfAllStudents was invoked");
        return studentRepository.findAll()
                .stream()
                .mapToInt(Student::getAge)
                .average()
                .orElse(0);
    }

    public void getAllStudentsWithThreads() {
        LOGGER.debug("Method getAllStudentsWithThreads was invoked");
        List<Student> students = studentRepository.findAll();
        printStudent(students.get(0));
        printStudent(students.get(1));

        new Thread(() -> {
            printStudent(students.get(2));
            printStudent(students.get(3));
        }).start();

        new Thread(() -> {
            printStudent(students.get(4));
            printStudent(students.get(5));
        }).start();
    };

    public void getAllStudentsWithSynchronizedThreads() {
        LOGGER.debug("Method getAllStudentsWithSynchronizedThreads was invoked");
        List<Student> students = studentRepository.findAll();
        printStudentSynchronized(students.get(0), students.get(1));

        new Thread(() -> {
            printStudentSynchronized(students.get(2), students.get(3));
        }).start();

        new Thread(() -> {
            printStudentSynchronized(students.get(4), students.get(5));
        }).start();
    };
    public void printStudent(Student student) {
        System.out.println(student.getName());
    }

    public synchronized void printStudentSynchronized(Student student, Student student2) {
        System.out.println(student.getName());
        System.out.println(student2.getName());
    }
}
