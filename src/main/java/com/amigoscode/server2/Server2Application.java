package com.amigoscode.server2;

import com.amigoscode.server2.enumeration.Status;
import com.amigoscode.server2.model.Server;
import com.amigoscode.server2.repository.ServerRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Server2Application {

	public static void main(String[] args) {
		SpringApplication.run(Server2Application.class, args);
	}

	@Bean
	CommandLineRunner run(ServerRepo serverRepo){
		return args-> {
			serverRepo.save(new Server(null, "192.168.1.22", "Ubuntu", "16gb", "pc pessoal",
					"http://localhost:8080/server/image/server1.png", Status.SERVER_UP));
			serverRepo.save(new Server(null, "192.168.1.32", "guerreiro", "8gb", "pc pessoal guerreiro",
					"http://localhost:8080/server/image/server1.png", Status.SERVER_UP));
			};
		}
	}

