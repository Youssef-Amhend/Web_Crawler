package com.crawler.crawler.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

// for now this works . todo : i will improve it in the future ofc. [do some research and then come back ]

@Configuration
public class CrawlerConfig {
  @Bean
  public RestClient restClient(){
    // todo : maybe i should add agent and stuff like that . aka add more complex stuff
    RestClient restClient = RestClient.create();
    return restClient;
  }
}