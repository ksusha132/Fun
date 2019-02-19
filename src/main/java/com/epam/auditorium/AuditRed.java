package com.epam.auditorium;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
public class AuditRed {
    @Value("#{'${red.name}'}")
    private String name;
    @Value("#{'${red.numberOfSeats}'}")
    private Integer numberOfSeats;
    @Value("#{'${red.vipSeats}'.split(',')}")
    private List<Integer> vipSeats;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
