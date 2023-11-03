package com.st6.ChristmasFlair;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChristmasFlairController {

    @GetMapping("/")
    public String getString() {
        return "ChristmasFlair Index";
    }

    //going to localhost:8080/stringtest/{var} should result in this.
    //we are passing a variable through the path mapping. typing localhost:8080/test/100 will give you "resourceId = 100"
    @GetMapping("vartest/{var}")
    public String getString(@PathVariable String var) {
        return "Variable test: var = " + var;
    }
}
