package cmms.System.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/System")
public class controller {


    @GetMapping("/checkings")
    public String Checkings(){
        return "Hello all I am validations Message in System Service ";
    }



}
