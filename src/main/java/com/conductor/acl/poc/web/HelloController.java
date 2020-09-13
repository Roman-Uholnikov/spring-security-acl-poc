package com.conductor.acl.poc.web;

import com.conductor.acl.poc.persistence.dao.NoticeMessageRepository;
import com.conductor.acl.poc.persistence.entity.NoticeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloController {

    @Autowired
    NoticeMessageRepository messageRepository;

    @RequestMapping(value = "/", produces = "application/json")
    @ResponseBody
    String home() {
        return "Hello World!";
    }

    @RequestMapping(value = "/messages", produces = "application/json")
    @ResponseBody
    List<NoticeMessage> messages() {
        return messageRepository.findAll();
    }

    @RequestMapping(value = "/messages", produces = "application/json",
            method = RequestMethod.POST)
    @ResponseBody
    NoticeMessage createMessage(@RequestBody NoticeMessage message) {
        return messageRepository.save(message);
    }
}
