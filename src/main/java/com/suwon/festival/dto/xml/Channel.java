package com.suwon.festival.dto.xml;

import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Channel {
  public String title;
  public String link;
  public String description;
  public String pubDate;

  @JacksonXmlElementWrapper(useWrapping = false)
  @JacksonXmlProperty(localName = "item")
  public List<EventItem> item;
}