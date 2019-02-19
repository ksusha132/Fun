package com.epam.auditorium;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
public class AuditGreen {
    @Value("#{'${green.name}'}")
    private String name;
    @Value("#{'${green.numberOfSeats}'}")
    private Integer numberOfSeats;
    @Value("#{'${green.vipSeats}'.split(',')}")
    private List<Integer> vipSeats;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
