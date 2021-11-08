package com.local.orders.controllers;

import com.local.orders.Services.AgentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/agents")
public class AgentsController {

    @Autowired
    AgentServices agentServices;

    //    http://localhost:2019/agents/agent/9
    @GetMapping(value = "/agent/{id}", produces = "application/json")
    public ResponseEntity<?> getAgentById(@PathVariable long id) {
        return new ResponseEntity<>(agentServices.findById(id), HttpStatus.OK);
    }
}
