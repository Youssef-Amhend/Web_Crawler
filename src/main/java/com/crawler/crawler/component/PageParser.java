package com.crawler.crawler.component;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

// todo : finish the test for this class
@Component
public class PageParser {

  // this function converts raw html to document that jsoup can handle .
  public List<String> extractLinksFromHtml(String html , String currUrl) {
    Document doc = Jsoup.parse(html,currUrl);
    return linkParser(doc);
  }

  // this needs to be a private function in the future .
  // this an easy function that simply parse the html that we gave it . with Jsoup .
  public List<String> linkParser(Document doc) {

    List<String> links = new ArrayList<>();
    Elements linkElements = doc.select("a[href]");

    // this function is to extract the links from the html by finding every
    for (Element link : linkElements){
      String href = link.attr("href");
      String text = link.text();
      String absUrl = link.attr("abs:href");
      if (!absUrl.isEmpty()){
        links.add(absUrl);
      }
    }
    return links;
  }

  // todo : we can add a linkParser with streams .
}
