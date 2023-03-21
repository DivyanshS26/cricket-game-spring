package com.tekion.cricketgamespring.controller;

import com.tekion.cricketgamespring.model.Player;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/cricket")
public class PlayerController {
    @GetMapping("player")
    public Player getPlayer(){
        return null;
    }
}
