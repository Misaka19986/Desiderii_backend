package backend.desiderii.desiderii_backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
    @RequestMapping("/hello")
    public String hello(){
        System.out.println("Success!\n");
        return "hello world";
    }
}
