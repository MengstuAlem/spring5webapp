package guru.springfreamwork.spring5webapp.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile ("en")
public class GreetingServiceImp implements GreetingService {
    @Override
    public String hello() {
        return "Hello in english ";
    }
}
