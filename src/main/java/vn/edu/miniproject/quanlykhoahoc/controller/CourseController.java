package vn.edu.miniproject.quanlykhoahoc.controller;

import jakarta.validation.Valid;
import vn.edu.miniproject.quanlykhoahoc.model.Course;
import vn.edu.miniproject.quanlykhoahoc.repository.CourseRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/courses")
public class CourseController {
    private final CourseRepository courseRepository;

    public CourseController(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @GetMapping
    public String list(@RequestParam(required = false) String keyword, Model model) {
        if (keyword != null && !keyword.isBlank()) {
            model.addAttribute("courses",
                    courseRepository.findByNameContainingIgnoreCaseOrInstructorContainingIgnoreCase(
                            keyword.trim(), keyword.trim()));
        } else {
            model.addAttribute("courses", courseRepository.findAll());
        }
        model.addAttribute("keyword", keyword == null ? "" : keyword);
        return "courses/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("course", new Course());
        model.addAttribute("pageTitle", "Thêm khóa học");
        model.addAttribute("formAction", "/courses");
        return "courses/form";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute("course") Course course,
                         BindingResult bindingResult,
                         Model model,
                         RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("pageTitle", "Thêm khóa học");
            model.addAttribute("formAction", "/courses");
            return "courses/form";
        }
        courseRepository.save(course);
        redirectAttributes.addFlashAttribute("successMessage", "Thêm khóa học thành công!");
        return "redirect:/courses";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model,
                               RedirectAttributes redirectAttributes) {
        Optional<Course> course = courseRepository.findById(id);
        if (course.isEmpty()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Không tìm thấy khóa học.");
            return "redirect:/courses";
        }
        model.addAttribute("course", course.get());
        model.addAttribute("pageTitle", "Cập nhật khóa học");
        model.addAttribute("formAction", "/courses/" + id + "/update");
        return "courses/form";
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable Long id,
                         @Valid @ModelAttribute("course") Course formCourse,
                         BindingResult bindingResult,
                         Model model,
                         RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            formCourse.setId(id);
            model.addAttribute("pageTitle", "Cập nhật khóa học");
            model.addAttribute("formAction", "/courses/" + id + "/update");
            return "courses/form";
        }
        Optional<Course> existing = courseRepository.findById(id);
        if (existing.isEmpty()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Không tìm thấy khóa học.");
            return "redirect:/courses";
        }
        formCourse.setId(id);
        courseRepository.save(formCourse);
        redirectAttributes.addFlashAttribute("successMessage", "Cập nhật khóa học thành công!");
        return "redirect:/courses";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model,
                         RedirectAttributes redirectAttributes) {
        Optional<Course> course = courseRepository.findById(id);
        if (course.isEmpty()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Không tìm thấy khóa học.");
            return "redirect:/courses";
        }
        model.addAttribute("course", course.get());
        return "courses/detail";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        if (courseRepository.existsById(id)) {
            courseRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("successMessage", "Xóa khóa học thành công!");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Không tìm thấy khóa học.");
        }
        return "redirect:/courses";
    }
}
