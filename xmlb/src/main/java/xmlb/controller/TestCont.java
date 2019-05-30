package xmlb.controller;

import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/test")
public class TestCont {

    @GetMapping("/{id}")
    public String Test(@PathVariable Long id) {
        return "RADI" + id;
    }
}
