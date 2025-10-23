package com.crawler.crawler.config;

import com.crawler.crawler.component.fetcher.HttpPageFetcher;
import com.crawler.crawler.component.fetcher.PageFetcher;
import com.crawler.crawler.component.fetcher.SeleniumPageFetcher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

// for now this works . todo : i will improve it in the future ofc. [do some research and then come back ]

@Configuration
public class CrawlerConfig {
  @Value("${crawler.fetcher.type}")
  private String fetcherType ;

  @Bean
  public PageFetcher pageFetcher(
      HttpPageFetcher httpPageFetcher ,
      SeleniumPageFetcher seleniumPageFetcher) {
    return "selenium".equals(fetcherType) ? seleniumPageFetcher : httpPageFetcher;
  }

  @Bean
  public RestClient restClient(){
    // todo : maybe i should add agent and stuff like that . aka add more complex stuff
    RestClient restClient = RestClient.create();
    return restClient;
  }
}