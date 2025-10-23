package com.crawler.crawler.service;


import com.crawler.crawler.component.fetcher.PageFetcher;
import com.crawler.crawler.component.parser.PageParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

// todo : add test for this class .

@Service
public class CrawlerService {
  private static final Logger log = LoggerFactory.getLogger(CrawlerService.class);
  private final PageFetcher pageFetcher;
  private final PageParser pageParser;

  public CrawlerService(PageFetcher pageFetcher, PageParser pageParser) {
    this.pageFetcher = pageFetcher;
    this.pageParser = pageParser;
  }

  // to track visited urls so that we don't have an infinite loop .
  private final Set<String> visitedUrls = ConcurrentHashMap.newKeySet();

  // the queue  of urls to crawl .
  private final Queue<String> urlQueue = new LinkedList<>();

  // TODO : this should be set by the user . of the api with limitaion ofc, tier or the use and paid or no .....;
  private static final int MAX_DEPTH = 5;
  private static final int MAX_PAGES = 15;

  public void crawl(String StartUrl){
    crawl(StartUrl , MAX_DEPTH );
  }

  // Fixme : this function doesn't work is unclear and probably we need to recode it with my own code .
  public void crawl(String startURL , int maxDepth) {
    // one thread for now
    visitedUrls.clear();
    urlQueue.clear();
    //adding the first url to the queue.
    urlQueue.add(startURL);

    int countStartUrlIsValid =0;
    int visitedPages = 0;
    while (visitedPages <= MAX_PAGES &&  !urlQueue.isEmpty()){
      String currUrl = urlQueue.poll();
      if (visitedUrls.contains(currUrl)) {
        continue;
      }
      try {
        //start :
        String rawHtml ="";

        try {
          rawHtml = pageFetcher.fetchPage(currUrl);
        } catch (RestClientException ex ){
          if (visitedPages == 0){
            log.warn("StartUrl Not Valid.");
            break;
          }else {
            log.warn("link error.");
            continue;
          }
        }
        if (rawHtml  == null || rawHtml.isEmpty()){
          continue;
        }
        visitedUrls.add(currUrl);
        List<String> extractedLinks = pageParser.extractLinksFromHtml(rawHtml , currUrl);
        if (visitedPages < MAX_PAGES){
          for (String link : extractedLinks ) {
            if(shouldCrawl(link , startURL) && !visitedUrls.contains(link)){
              urlQueue.add(link);
            }
          }
        }
        System.out.println("url queue:");
        System.out.println(urlQueue);
        visitedPages++;
      } catch (Exception e){
        //todo : fix the exception handling and make it clear .
        log.warn("e: ", e);
      }
    }
  }

  public void printCrawlResults(){
    System.out.println(visitedUrls);
  }

  private boolean shouldCrawl(String url , String startUrl){
    // todo : add more robust validation for if we should crawl it .
    return url != null && !url.isEmpty() && !visitedUrls.contains(url);
  }
}
