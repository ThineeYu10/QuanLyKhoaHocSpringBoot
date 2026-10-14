package vn.edu.miniproject.quanlykhoahoc.repository;

import vn.edu.miniproject.quanlykhoahoc.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByNameContainingIgnoreCaseOrInstructorContainingIgnoreCase(
            String name, String instructor);
}
