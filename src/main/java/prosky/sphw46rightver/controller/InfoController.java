package prosky.sphw46rightver.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.LongStream;

@RestController
@RequestMapping("/info")
public class InfoController {

    @Value("${server.port}")
    private int port;

    @GetMapping("/port") //GET http://localhost:8080/getPort
    public int getPort() {
        return port;
    }

    @GetMapping("/findNumber")
    public long findNumber() {
        return LongStream.rangeClosed(1, 1_000_000)
                .reduce(0L, Long::sum);
    }

}
