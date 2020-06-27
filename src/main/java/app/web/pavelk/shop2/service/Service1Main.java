package app.web.pavelk.shop2.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class Service1Main {

    @Value("${hostname}")
    private String hostname;

    @Value("${server.port}")
    private String port;


    public String getHostname() {
        return hostname;
    }

    public String getPort() {
        return port;
    }
}
