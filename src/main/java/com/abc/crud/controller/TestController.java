package com.abc.crud.controller;


import com.abc.crud.entity.Department;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {

    @Value("${profile}")
    private String message;


    @GetMapping("/")
    public String home() {
        return "Message: " + message;
    }
    @PostMapping("/testData/{id}")
    public String testApi(@PathVariable Long id,
                          @RequestParam(name = "abc") String abcd,
                          @RequestBody Department db) {
        System.out.println("Id: " + id);
        System.out.println("RequestParam: : " + abcd);
        System.out.println(db.toString());
        return "GotIt";
    }
}
