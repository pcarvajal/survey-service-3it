package cl.threeit.api.controllers;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Hidden
@RestController
public class HomeController {
    @RequestMapping("/")
    public void  index(HttpServletResponse httpServletResponse) throws IOException {
        httpServletResponse.sendRedirect("swagger-ui.html");
    }
}
