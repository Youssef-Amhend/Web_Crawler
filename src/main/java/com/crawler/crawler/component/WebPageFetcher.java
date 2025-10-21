package com.crawler.crawler.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;


@Component
public class WebPageFetcher {
  private static final Logger log = LoggerFactory.getLogger(WebPageFetcher.class);
  // this is to fetch the page .
  // todo : use http client instead of RestClient .
  // todo : add error verification .
  // todo : add logging .

  private final RestClient restClient;
  public WebPageFetcher(RestClient restClient){
    this.restClient = restClient;
  }

  // to track the call , i want to check the startUrl and if it's not valid then we should send that the link is not valid
  int visitedPages =0;

  public String fetchPage(String URI) {
    String htmlResult;
    try {
      htmlResult = restClient.get()
          .uri(URI)
          .retrieve()
          .body(String.class);
      System.out.println(htmlResult);
      visitedPages++;
      return htmlResult;
    } catch (RestClientException e){
      log.warn("the Link is not valid.");
      if (visitedPages == 0){
        log.warn("StartUrl Not Valid");
        visitedPages++;
      }
      else {
        log.warn("CurrLink not Valid");
        visitedPages++;
      }
      return null;
    }
  }
}
