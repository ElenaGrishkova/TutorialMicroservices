package ru.diasoft.tutorial.microservices.module10;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import ru.diasoft.tutorial.microservices.module10.kafka.ConsumerChannels;

@SpringBootApplication
@EnableBinding(ConsumerChannels.class)
public class Module10Application {

	public static void main(String[] args) {
		SpringApplication.run(Module10Application.class, args);
	}

}
