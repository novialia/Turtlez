package project_sc.turtlez;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/home")
public class HomeController {
    
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
	public SolveResponse getSearchResultViaAjax(@RequestParam String name) {
    	
    	SolveDormate solver = new SolveDormate();
    	solver.solve(name);
    	User me = solver.getMe();
    	User[] result = solver.getUsers().toArray(new User[solver.getUsers().size()]);
    	Double[] rates = solver.getCompatibilityRate().toArray(new Double[solver.getCompatibilityRate().size()]);
    	double avgRate = solver.getAvgRate();
    	
    	SolveResponse resp = new SolveResponse();
    	resp.setAverage_rate(avgRate);
    	resp.setRates(rates);
    	resp.setResult(result);
    	resp.setMe(me);
    	
    	return resp;
	}

}