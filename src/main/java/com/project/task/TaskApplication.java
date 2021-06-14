package com.project.task;

import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.project.task.dao.DaoRooms;
import com.project.task.models.Room;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class TaskApplication {

	public static void main(String[] args) throws IOException, GeoIp2Exception {

		SpringApplication.run(TaskApplication.class, args);



	}

}
