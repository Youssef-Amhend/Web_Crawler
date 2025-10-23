package com.crawler.crawler.component;

import com.crawler.crawler.component.parser.PageParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PageParserTest {
  private PageParser pageParser;

  @BeforeEach
  void setuUp(){
    pageParser = new PageParser();
  }

  // this is just an example for learning .
  @Test
  void testNameOfFunction() {
    // ARRANGE - Set up test data
    // ACT - Call the method you're testing
    // ASSERT - Check the result is what you expected
  }

  @Test
  void testShouldExtractOneLink(){
    // Arrange
    String startUrl = "";
    String html = "<a href='https://google.com'>Google</a>";

    //here we are calling the method that we are testing.
    List<String> links = pageParser.extractLinksFromHtml(html,startUrl);

    //assert :
    assertEquals(1 , links.size());
    assertEquals("https://google.com" , links.getFirst());
  }

  @Test
  void testLinkParser(){
    //Arrange
    String html = "<a href='https://google.com'>Google</a>";
    Document doc = Jsoup.parse(html);

    //act aka call the method we are testing
    List<String> links = pageParser.linkParser(doc);

    //assertions :
    List<String> testString = new ArrayList<>();
    testString.add("https://google.com");
    assertEquals(testString,links);
  }

  @Test
  void shouldExtractAbsUrisFromHref() {
    //Arrange
    String html = "<a href='/page1'>Google</a> <a href='https://google.com/page2'>Google</a>";
    String baseUri = "https://google.com";
    Document doc = Jsoup.parse(html,baseUri);

    //act aka call the method we are testing
    List<String> links = pageParser.linkParser(doc);

    //assertions :
    List<String> testString = new ArrayList<>();
    testString.add("https://google.com/page1");
    testString.add("https://google.com/page2");
    System.out.println(links);
    assertEquals(testString,links);
  }


  //Todo: test edge cases : 1.null html 2.null returned list 3.use brain


}