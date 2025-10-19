package com.crawler.crawler.component;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PageParser {

  // this function converts raw html to document that jsoup can handle .
  public List<String> extractLinksFromHtml(String html) {
    Document doc = Jsoup.parse(html);
    return linkParser(doc);
  }

  // this an easy function that simply parse the html that we gave it . with Jsoup .
  public List<String> linkParser(Document doc) {

    List<String> links = new ArrayList<>();
    Elements linkElements = doc.select("a[href]");

    for (Element link : linkElements){
      String href = link.attr("href");
      String text = link.text();
      String absUrl = link.attr("abs:href");

      links.add(href);
    }
    return links;
  }
}
