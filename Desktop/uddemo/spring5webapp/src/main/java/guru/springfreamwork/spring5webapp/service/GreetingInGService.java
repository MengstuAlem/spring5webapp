package guru.springfreamwork.spring5webapp.service;

import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile ("gn")
public class GreetingInGService implements GreetingService {
    @Override
    public String hello() {
        return "helle in germeny ";
    }
}
