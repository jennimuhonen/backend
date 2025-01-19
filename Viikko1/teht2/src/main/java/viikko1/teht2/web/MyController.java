package viikko1.teht2.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController {
	@RequestMapping("/hello")
    @ResponseBody
    public String returnMessage(@RequestParam (name="location", required=false, defaultValue="unknown place") String locationValue, @RequestParam (name="name", required=false, defaultValue="Anonymous") String nameValue) {
        return "Welcome to the " + locationValue + " " + nameValue + "!";
    }
	
}
