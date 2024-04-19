package com.dxc.meetingroomreservationsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class MeetingroomReservationSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(MeetingroomReservationSystemApplication.class, args);
    }

}
