package com.crawler.crawler.services;


import com.crawler.crawler.component.PageParser;
import com.crawler.crawler.component.WebPageFetcher;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class CrawlerServices {
  private final WebPageFetcher webPageFetcher;
  private final PageParser pageParser;
  public CrawlerServices(WebPageFetcher webPageFetcher,PageParser pageParser) {
    this.webPageFetcher = webPageFetcher ;
    this.pageParser = pageParser;
  }

  // to track visited urls so that we don't have an infinite loop .
  private final Set<String> visitedUrls = ConcurrentHashMap.newKeySet();

  // the queue  of urls to crawl .
  private final Queue<String> urlQueue = new LinkedList<>();

  // TODO : this should be set by the user . of the api with limitaion ofc, tier or the use and paid or no .....;
  private static final int MAX_DEPTH = 5;
  private static final int MAX_PAGES = 10;

  public void crawl(String StartUrl){
    crawl(StartUrl , MAX_DEPTH );
  }

  public void crawl(String startURL , int maxDepth) {
    // one thread for now
    visitedUrls.clear();
    urlQueue.clear();
    //adding the first url to the queue .
    urlQueue.add(startURL);

    int visitedPages = 0;

    while (visitedPages <= MAX_PAGES &&  !urlQueue.isEmpty()){


      visitedPages++;
    }

    String rawHtml = webPageFetcher.fetchPage(startURL);
    List<String> extractedLinks = pageParser.extractLinksFromHtml(rawHtml);
    System.out.println(extractedLinks);
  }
}
