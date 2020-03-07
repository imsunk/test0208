package test.example.org.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import test.example.org.web.dto.HelloResponseDto;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello(){
        return "Hello";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name") String name, @RequestParam("amount") int amount){
        return new HelloResponseDto(name, amount);
    }
    @GetMapping("/hello/dto2")
    public HelloResponseDto helloDto(@RequestParam("name") String name, @RequestParam("amount2") int amount2){
        return new HelloResponseDto(name, amount2);
    }
    @GetMapping("/hello/dto3")
    public HelloResponseDto helloDto(@RequestParam("name") String name, @RequestParam("amount2") int amount2){
        return new HelloResponseDto(name, amount2);
    }
}