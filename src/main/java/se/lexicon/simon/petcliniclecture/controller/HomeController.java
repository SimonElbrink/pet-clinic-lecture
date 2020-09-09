package se.lexicon.simon.petcliniclecture.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping
    public ResponseEntity<String> hello(){
        return ResponseEntity.ok("Hello Form the Inside of Pet Clinic");
    }


}
