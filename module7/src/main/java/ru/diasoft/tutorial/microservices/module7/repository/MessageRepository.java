package ru.diasoft.tutorial.microservices.module7.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.diasoft.tutorial.microservices.module7.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
