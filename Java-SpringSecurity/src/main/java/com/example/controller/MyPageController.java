package com.example.controller;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class MyPageController {
    
    @GetMapping("/mypage")
    public String mypage() {
        return "mypage";
    }

    @GetMapping("/JSONPlaceholder")
    public String JSONPlaceholder(){
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create("https://jsonplaceholder.typicode.com/posts/1"))
        .GET()
        .build();

         try {                                                                                                                                                                                                                                                                                                           
      HttpResponse<String> response = client.send(request, BodyHandlers.ofString()); 
      System.out.println(response.body());                                                                                                                                                                                                                               
      return "mypage";                                                                                                                                                                                                                                                                                            
  } catch (IOException | InterruptedException e) {                                                                                                                                                                                                                                                                  
      return "error";
  }             
        

    }
    
}
