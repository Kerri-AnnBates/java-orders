package com.local.orders.Services;

import com.local.orders.models.Agent;
import com.local.orders.repositories.AgentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

@Transactional
@Service
public class AgentServicesImpl implements AgentServices{
    @Autowired
    private AgentsRepository agentsRepository;

    @Transactional
    @Override
    public Agent save(Agent agent) {
        return agentsRepository.save(agent);
    }

    @Override
    public Agent findById(long id) {
        return agentsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Agent id " + id + " not found!"));
    }

    @Transactional
    @Override
    public void delete(long id) {
        Agent agent = agentsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Agent id " + id + " not found!"));

        if(agentsRepository.findById(id).isPresent()) {
            if(agent.getCustomers().size() > 0) {
                throw new EntityExistsException("Found a customer for Agent " + id);
            } else {
                agentsRepository.deleteById(id);
            }
        } else {
            throw new EntityNotFoundException("Agent " + id + " not found");
        }
    }
}
