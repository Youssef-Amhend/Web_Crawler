package com.crawler.crawler.controllers;


import com.crawler.crawler.services.CrawlerServices;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/crawler")
public class CrawlerController {
  private final CrawlerServices crawlerServices ;

  public  CrawlerController (CrawlerServices crawlerServices){
    this.crawlerServices = crawlerServices;
  }

  //TODO  :  add validation for the URL and make sure if it's HTTPS or HTTP . and probably we need to add the depth in the future .
  @PostMapping()
  public String StartURL (@RequestBody String URL) {
    crawlerServices.crawl(URL);
    return "Starting URL";
  }
}