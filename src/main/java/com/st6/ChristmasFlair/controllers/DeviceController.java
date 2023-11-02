package com.st6.ChristmasFlair.controllers;

import com.st6.ChristmasFlair.models.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*") // Annotation to allow requests from any origin.
@RestController // Annotation to indicate that this class is a REST controller.
@RequestMapping("/device")
public class DeviceController implements ApplicationListener<ApplicationReadyEvent> {
    private static final Logger logger = LoggerFactory.getLogger(DeviceController.class);
    private final Map<String, Device> devices = new HashMap<>();

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        this.devices.put("Raspberry Pi 3 B+", new StripDevice("Raspberry Pi 3 B+", 18, 255, 100));
    }

}