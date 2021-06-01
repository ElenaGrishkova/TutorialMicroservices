package ru.diasoft.tutorial.microservice.module4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.diasoft.tutorial.microservice.module4.entiry.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
