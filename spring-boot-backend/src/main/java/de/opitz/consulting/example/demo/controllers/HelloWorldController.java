package de.opitz.consulting.example.demo.controllers;

import javax.activation.MimeType;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloWorldController {
    Logger log = LoggerFactory.getLogger(HelloWorldController.class);

    /**
     * Hello World
     * @return
     */
    @GetMapping("/hello")
    public String index() {
        log.info("index called");
        return "Hello From HelloWorldConroller!";
    }
    /**
     * Hello World with request parameter and language header
     * @param name
     * @param language
     * @return
     */
    @GetMapping("/hello2")
    public String helloWithParam(
        @RequestParam(value="name", defaultValue="World") String name,
        @RequestHeader("accept-language") String language
        ){
        log.info("helloWithParam called langguage:{}", language);
        return "Hello "+ name;
    }

    /**
     *  mapping by HTTP-verb
     * @return
     */
    @PostMapping("/hello")
    public String postHello() {
        log.info("postHello called");
        return "Received Post";
    }

    /**
     * Hello World with path-parameter
     * @param name
     * @return
     */
    @GetMapping("/hello/{name}")
    public String getElement(@PathVariable String name) {
        log.info("getElement called {}", name);
        return "Hello " + name;
    }

    /**
     * mapping by requested document type
     * @return
     */
    @RequestMapping(value = "/hello", method = RequestMethod.GET, produces = "text/html")
    public String getType() {
        log.info("index called");
        return "<!doctype html>\n" + 
                "<html><head><title>hello</title></head>\n" + 
                "<body>\n" + "<h1>Hello From HelloWorldConroller!" + "</h1>\n" + 
                "</body></html>";
    }

    /**
     * get requested url, and all othe request parameters
     * @param request
     * @return
     */
    @GetMapping("/hello3/**")
    public String getAnyPath(HttpServletRequest request) {
        log.info("getAnyPath called {}", request.getRequestURI() );
        return "Hello 3 " + request.getRequestURI();
    }

}
