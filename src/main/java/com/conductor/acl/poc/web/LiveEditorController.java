package com.conductor.acl.poc.web;

import com.conductor.acl.poc.persistence.entity.LiveEditorChange;
import com.conductor.acl.poc.service.LiveEditorService;
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
    LiveEditorService liveEditorService;


    @RequestMapping(value = "/", produces = "application/json")
    @ResponseBody
    String home() {
        return "Hello World!";
    }


    @RequestMapping(value = "/changes", produces = "application/json")
    @ResponseBody
    List<LiveEditorChange> changes() {
        return liveEditorService.findAll();
    }

    @RequestMapping(value = "/changes", produces = "application/json",
            method = RequestMethod.POST)
    @ResponseBody
    LiveEditorChange createMessage(@RequestBody LiveEditorChange change) {
        return liveEditorService.createChange(change);
    }
}
