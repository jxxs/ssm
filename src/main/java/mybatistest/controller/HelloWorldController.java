package mybatistest.controller;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;



/**
 * Created by sunj on 2016/5/26.
 */
@Controller
@RequestMapping(value = "test")
public class HelloWorldController {

    public HelloWorldController(){
        System.out.println("加载HelloWorldController。。。。。。。。。。。。。。。。。。。。。");
    }
    @RequestMapping(value = "helloWorld")
    public String helloWorld(Model model){
        model.addAttribute("msg","Hello World!");
        return "helloWorld";
    }
}
