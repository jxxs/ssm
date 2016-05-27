package mybatistest.controller;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by sunj on 2016/5/26.
 */
@Controller
@RequestMapping("test")
public class HelloWorldController {

    @RequestMapping("helloWorld")
    public String helloWorld(){
        return "helloWorld";
    }
}
