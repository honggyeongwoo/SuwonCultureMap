package com.suwon.festival.dto.xml;

import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Photos {
  @JacksonXmlElementWrapper(useWrapping = false)
  @JacksonXmlProperty(localName = "photoInfo")
  public List<PhotoInfo> photoInfo;
}