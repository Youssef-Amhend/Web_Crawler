package com.crawler.crawler.component;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

@Component
public class WebPageFetcher {
  // this is to fetch the page .
  public String fetchPage(String URI) {
    RestClient restClient = RestClient.create();

    return restClient.get()
        .uri(URI)
        .retrieve()
        .body(String.class);
  }
}
