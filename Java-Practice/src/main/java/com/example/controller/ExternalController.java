package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class ExternalController {
    
    // コメント記述
    @GetMapping("external")
    public String external() {
        return new String();
    }

    // 天気APIを呼び出す（最初は下手書きにしてるけどのちに環境変数に移す予定）
    @GetMapping("weather")
    public String weather() {
        // 天気API無料枠を調べられなかったからまた今度にする
        return "hoge";
    }
    
    
}
