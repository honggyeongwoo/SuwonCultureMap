package com.suwon.festival.dto.xml;

import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class ContentInfo {
  @JacksonXmlElementWrapper(useWrapping = false)
  @JacksonXmlProperty(localName = "contentInfoDetail")
  public List<ContentInfoDetail> contentInfoDetail;
}