package com.samagra.Application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.samagra.Service.OutBoundService;
import com.samagra.notification.Response.MessageResponse;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
@RestController
@RequestMapping(value = "/parse")
public class GupshupConsumer {
  @Autowired
  private OutBoundService ms3Service;

  @KafkaListener(id = "message", topics = "${gupshup-incoming-message}")
  public void consumeMessage(String message) throws Exception {
    XmlMapper xmlMapper = new XmlMapper();
    MessageResponse value = xmlMapper.readValue(message, MessageResponse.class);

    // log.info("Consumer got message: {}", value.getPayload().getSender().getName());

    if(value.getPayload().getPayload().getText() != null ||
            value.getPayload().getType().getCategory().equals("image") ||
            value.getPayload().getPayload().getType().equals("text")
    )
      ms3Service.processKafkaInResponse(value);
  }

  @KafkaListener(id = "userevent", topics = "${gupshup-opted-out}")
  public void consumeOptedOutMessage(String message)
      throws JsonMappingException, JsonProcessingException {
    XmlMapper xmlMapper = new XmlMapper();
    MessageResponse value = xmlMapper.readValue("message",MessageResponse.class);

    log.info("Consumer got message: {}", value);
  }
}
