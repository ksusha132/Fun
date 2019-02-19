package com.epam.auditorium;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import java.util.List;

@Configuration
@Getter
public class AuditBlue {
    @Value("#{'${blue.name}'}")
    private String name;
    @Value("#{'${blue.numberOfSeats}'}")
    private Integer numberOfSeats;
    @Value("#{'${blue.vipSeats}'.split(',')}")
    private List<Integer> vipSeats;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
