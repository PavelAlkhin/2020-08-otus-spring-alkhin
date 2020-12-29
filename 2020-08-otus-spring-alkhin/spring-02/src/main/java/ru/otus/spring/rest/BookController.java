package ru.otus.spring.rest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BookController {

    @GetMapping("/")
    public String indexPage(Model model) { return "index"; }

    @GetMapping("/books")
    public String listPage(Model model) { return "books"; }

    @GetMapping("/edit")
    public String editPage(@RequestParam(value = "id", required = false) String id, Model model) { return "edit"; }

    @GetMapping("/newbook")
    public String newbook(Model model) { return "newbook"; }

    @GetMapping("/admin")
    public String adminPage(Model model) { return "admin"; }
}
