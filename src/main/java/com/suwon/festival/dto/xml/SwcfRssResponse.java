package com.suwon.festival.dto.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "rss")
public class SwcfRssResponse {
  public Channel channel;
}