package com.amigoscode.server2.resource;

import com.amigoscode.server2.enumeration.Status;
import com.amigoscode.server2.model.Response;
import com.amigoscode.server2.model.Server;
import com.amigoscode.server2.service.implementation.ServerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import static java.time.LocalDateTime.now;
import static java.util.Map.*;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

@RestController
@RequestMapping("/server")
@RequiredArgsConstructor
public class ServerResource {
    private final ServerServiceImpl serverService;

    @GetMapping("/list")
    public ResponseEntity<Response> getServer(){

        return ResponseEntity.ok(
        Response.builder()
                .timestamp(now())
                .data(of("servers",serverService.list(30)))
                .message("server retrieved")
                .status(OK)
                .statusCode(OK.value())
                .build()
        );
    }

    @GetMapping("/ping/{ipAddress}")
    public ResponseEntity<Response> pingServer(@PathVariable("ipAddress") String ipAddress) throws IOException {
        Server server = serverService.ping(ipAddress);
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(now())
                        .data(of("servers",server))
                        .message(server.getStatus()== Status.SERVER_UP ? "ping success" : "ping failed")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @GetMapping("/save")
    public ResponseEntity<Response> saveServer(@RequestBody @Valid Server server) {

        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(now())
                        .data(of("server",serverService.create(server)))
                        .message("server criado")
                        .status(CREATED)
                        .statusCode(CREATED .value())
                        .build()
        );
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Response> getServer(@PathVariable("id") Long id) {

        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(now())
                        .data(of("servers",serverService.get(id)))
                        .message("server retrieved ")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> deleteServer(@PathVariable("id") Long id) {

        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(now())
                        .data(of("deleted",serverService.delete(id)))
                        .message("server deleted ")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }
    @GetMapping(value = "/images/{fileName}", produces = IMAGE_PNG_VALUE)
    public byte[]  getServerImage(@PathVariable("fileName") String fileName) throws IOException {

    return Files.readAllBytes(Paths.get(System.getProperty("user.home") + "/Downloads/images" + fileName));
    }



}
