package com.local.orders.Services;

import com.local.orders.models.Agent;
import com.local.orders.repositories.AgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class AgentServicesImpl implements AgentServices{
    @Autowired
    private AgentRepository agentRepository;

    @Transactional
    @Override
    public Agent save(Agent agent) {
        return agentRepository.save(agent);
    }
}
