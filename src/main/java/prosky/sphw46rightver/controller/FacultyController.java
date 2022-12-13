package prosky.sphw46rightver.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import prosky.sphw46rightver.entity.Faculty;
import prosky.sphw46rightver.service.FacultyService;

import java.util.Collection;

@RestController
@RequestMapping(path = "faculty")
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping  // POST http:localhost:8080/faculty
    public ResponseEntity<Faculty> createFaculty(@RequestBody Faculty faculty) {
        Faculty newFaculty = facultyService.createFaculty(faculty);
        if (newFaculty == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(newFaculty);
    }

    @GetMapping("{id}")  // GET http:localhost:8080/faculty/1
    public ResponseEntity<Faculty> getFaculty(@PathVariable Long id) {
        Faculty faculty = facultyService.getFaculty(id);
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }


    @PutMapping  // PUT http:localhost:8080/faculty
    public ResponseEntity<Faculty> updateFaculty(@RequestBody Faculty faculty) {
        Faculty newFaculty = facultyService.updateFaculty(faculty);
        if (newFaculty == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(newFaculty);
    }

    @DeleteMapping(path = "{id}")  // DELETE http:localhost:8080/faculty/1
    public ResponseEntity deleteFaculty(@PathVariable Long id) {
        facultyService.deleteFaculty(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/getAll")  // GET http:localhost:8080/faculty/getAll
    public ResponseEntity<Collection<Faculty>> getAll() {
        Collection<Faculty> faculties = facultyService.getAll();
        if (faculties == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculties);
    }

    @GetMapping // GET http:localhost:8080/faculty
    public ResponseEntity<Collection<Faculty>> sortFacultiesByNameOrColor(@RequestParam(required = false) String name,
                                                                          @RequestParam(required = false) String color) {
        if (name != null && !name.isBlank()) {
            return ResponseEntity.ok(facultyService.findFacultiesByName(name));
        }
        if (color != null && !color.isBlank()) {
            return ResponseEntity.ok(facultyService.findFacultiesByColor(color));
        }
        return ResponseEntity.ok(facultyService.getAll());
    }
    @GetMapping("/longestFacultyName")
    public ResponseEntity<String> findLongestFacultyName() {
        return ResponseEntity.ok(facultyService.findLongestFacultyName());
    }
}
