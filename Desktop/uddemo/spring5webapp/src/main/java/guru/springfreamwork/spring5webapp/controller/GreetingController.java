package guru.springfreamwork.spring5webapp.controller;

import guru.springfreamwork.spring5webapp.service.GreetingService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class GreetingController {

    GreetingService greetingService;

    public GreetingController( GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @RequestMapping("/greeting")
    public String hello(){
        return greetingService.hello ();
    }
}
