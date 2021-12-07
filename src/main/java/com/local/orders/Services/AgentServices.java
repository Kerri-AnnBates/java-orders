package com.local.orders.Services;

import com.local.orders.models.Agent;

public interface AgentServices {
    Agent save(Agent agent);
    Agent findById(long id);
    void delete(long id);
}
