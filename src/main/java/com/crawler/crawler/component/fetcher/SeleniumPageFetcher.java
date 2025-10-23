package com.crawler.crawler.component.fetcher;

import org.springframework.stereotype.Component;

@Component
public class SeleniumPageFetcher implements PageFetcher{
  @Override
  public String fetchPage(String url) {
   // the Selenium logic in here.
    return "";
  }
}
