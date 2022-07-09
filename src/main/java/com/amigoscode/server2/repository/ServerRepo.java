package com.amigoscode.server2.repository;

import com.amigoscode.server2.model.Server;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServerRepo extends JpaRepository<Server, Long > {

    Server findByIpAddress(String ipAddress);
}
