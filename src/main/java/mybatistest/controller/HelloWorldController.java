package mybatistest.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Created by sunj on 2016/5/26.
 */
@Controller
@RequestMapping("test")
class HelloWorldController {

    @RequestMapping("helloWorld")
    public String helloWorld(){
        return "helloWorld";
    }
}
