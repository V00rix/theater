package controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController extends ControllerBase {
    /**
     * Toggle play/pause
     */
    @RequestMapping(value = "/test")
    public String test() {
        return "sd";
    }
}
