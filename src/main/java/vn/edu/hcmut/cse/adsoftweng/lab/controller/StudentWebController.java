package vn.edu.hcmut.cse.adsoftweng.lab.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller; // Lưu ý: dùng

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmut.cse.adsoftweng.lab.service.StudentService;
import vn.edu.hcmut.cse.adsoftweng.lab.entity.Student;
import java.util.List;
@Controller
@RequestMapping("/students")
public class StudentWebController {
    @Autowired
    private StudentService service;
    // Route: GET http://localhost:8080/students
    @GetMapping
    public String getAllStudents(@RequestParam(required = false) String keyword, Model model) {
        List<Student> students;
        if (keyword != null && !keyword.isEmpty()) {
            students = service.searchByName(keyword);
        }
        else {
            students = service.getAll();
        }
        model.addAttribute("dsSinhVien", students);
        return "students.html";
    }
    @GetMapping("/{id}")
    public String getStudentById(@PathVariable String id, Model model) {
        Student student = service.getById(id);
        model.addAttribute("student", student);
        return "student-detail.html";
    }
    @GetMapping("/new")
    public String getNewStudent(Model model) {
        model.addAttribute("student", new Student());
        model.addAttribute("isEdit", false);
        return "student-form.html";
    }
    @GetMapping("/edit/{id}")
    public String getEditStudent(@PathVariable String id,Model model) {
        model.addAttribute("student", service.getById(id));
        model.addAttribute("isEdit", true);
        return "student-form.html";
    }
    @PostMapping("/update")
    public String updateStudent(@ModelAttribute Student student, Model model) {
        service.save(student);
        return "redirect:/students";
    }
    @PostMapping
    public String addStudent(@ModelAttribute Student student, Model model) {
        try{
            service.addStudent(student);
            return "redirect:/students";
        }
        catch(Exception e){
            model.addAttribute("error", e.getMessage());
            model.addAttribute("student", student);
            model.addAttribute("isEdit", false);
            return "student-form";
        }
    }
    @PostMapping("/{id}/delete")
    public String deleteStudent(@PathVariable String id) {
        service.delete(id);
        return  "redirect:/students";
    }
}
