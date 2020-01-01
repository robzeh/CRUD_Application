package com.robie.crud.controller;

import com.robie.crud.entity.Teacher;
import com.robie.crud.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/teachers/")
public class TeacherController {

    private final TeacherRepository teacherRepository;

    @Autowired
    public TeacherController(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @GetMapping("singup")
    public String showSignUpForm(Teacher teacher) {
        return "add-teacher";
    }

    @GetMapping("list")
    public String showUpdateForm(Model model) {
        model.addAttribute("teachers", teacherRepository.findAll());
        return "teachers";
    }

    @PostMapping("add")
    public String addTeacher(@Valid Teacher teacher, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-teacher";
        }

        teacherRepository.save(teacher);
        return "redirect:list";
    }

    @GetMapping("edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid teacher Id: " + id));
        model.addAttribute("teacher", teacher);
        return "update-teacher";
    }

    @PostMapping("update/{id}")
    public String updateTeacher(@PathVariable("id") Long id, @Valid Teacher teacher, BindingResult result, Model model) {
        if (result.hasErrors()) {
            teacher.setId(id);
            return "update-teacher";
        }

        teacherRepository.save(teacher);
        model.addAttribute("teachers", teacherRepository.findAll());
        return "teachers";
    }

    @GetMapping("delete/{id}")
    public String deleteTeacher(@PathVariable("id") Long id, Model model) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid teacher Id: " + id));
        teacherRepository.delete(teacher);
        model.addAttribute("teachers", teacherRepository.findAll());
        return "teachers";
    }



}
