package com.conductor.acl.poc.web;

import com.conductor.acl.poc.persistence.dao.LiveEditorChangeRepository;
import com.conductor.acl.poc.persistence.entity.LiveEditorChange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LiveEditorController {

    @Autowired
    LiveEditorChangeRepository messageRepository;


    @RequestMapping(value = "/", produces = "application/json")
    @ResponseBody
    String home() {
        return "Hello World!";
    }


    @RequestMapping(value = "/changes", produces = "application/json")
    @ResponseBody
    List<LiveEditorChange> changes() {
        return messageRepository.findAll();
    }

    @RequestMapping(value = "/changes", produces = "application/json",
            method = RequestMethod.POST)
    @ResponseBody
    LiveEditorChange createMessage(@RequestBody LiveEditorChange change) {
        LiveEditorChange change1 = messageRepository.save(change);
        // TODO ruholnikov: 14.09.2020 save to acl object identity
        return change1;
    }
}
