package com.local.orders.repositories;

import com.local.orders.models.Agent;
import org.springframework.data.repository.CrudRepository;

public interface AgentRepository extends CrudRepository<Agent, Long> {
}
