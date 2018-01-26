package project_sc.turtlez;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller("/")
public class IndexController {

    @RequestMapping(method = RequestMethod.GET, produces = "text/html")
    public String indexGet(Model model) {
    	UserGenerator ug = new UserGenerator();
    	String[] names = ug.generateNames();
    	model.addAttribute("names", names);
        return "index";
    }
    
}