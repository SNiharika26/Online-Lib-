package com.example.online.lib.controler;

import org.springframework.ui.Model;
import com.example.online.lib.entity.Book;
import com.example.online.lib.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private BookService bookService;
    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @GetMapping("/")
    public String home(Model model){

        List< Book> books=bookService.getAllBooks();
        model.addAttribute("books",books);
        return "book";
    }

}

