package net.codeforyou.springbootthtmeleafcrud.Controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.codeforyou.springbootthtmeleafcrud.Entity.Student;
import net.codeforyou.springbootthtmeleafcrud.Repository.StudentRepository;

@Controller
@RequestMapping("/students/")
public class StudentController {

    private static final String htmlMapping = "students";

    private static final String homePage = "index";

    private final StudentRepository studentRepository;

    @Autowired
    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping("signup")
    public String showSignUpForm(Student student) {
        return "add-student";
    }

    @GetMapping("list")
    public String showUpdateForm(Model model) {
        model.addAttribute(htmlMapping, studentRepository.findAll());
        return homePage;
    }

    @PostMapping("add")
    public String addStudent(@Valid Student student, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-student";
        }
        studentRepository.save(student);
        return "redirect:list";
    }

    @GetMapping("edit/{id}")
    public String showUpdateForm(@PathVariable("id") String id, Model model) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Student Id:" + id));
        model.addAttribute("student", student);
        return "update-student";
    }

    @PostMapping("update/{id}")
    public String updateStudent(@PathVariable("id") String id, @Valid Student student, BindingResult result,
            Model model) {
        if (result.hasErrors()) {
            student.setId(id);
            return "update-student";
        }

        studentRepository.save(student);
        model.addAttribute(htmlMapping, studentRepository.findAll());
        return homePage;
    }

    @GetMapping("delete/{id}")
    public String deleteStudent(@PathVariable("id") String id, Model model) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Student Id:" + id));
        studentRepository.delete(student);
        model.addAttribute(htmlMapping, studentRepository.findAll());
        return homePage;
    }

}
