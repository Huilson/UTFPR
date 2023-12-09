package br.com.utfpr.hotelpet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HotelPetControler {

    @GetMapping("/")
    public String index() {
        return "index";
    }
}
