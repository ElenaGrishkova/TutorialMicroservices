package ru.diasoft.tutorial.microservice.module4.restcontroller;

import org.springframework.web.bind.annotation.*;
import ru.diasoft.tutorial.microservice.module4.service.MessageService;
import ru.diasoft.tutorial.microservice.module4.entiry.Message;
import ru.diasoft.tutorial.microservice.module4.exception.NotFoundException;

import java.util.List;

@RestController
@RequestMapping("messages")
public class MessageController {
    
    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }


    @GetMapping
    public List<Message> getAllMessages() {
        return messageService.findAll();
    }

    @GetMapping("{id}")
    public Message getMessageByID(@PathVariable("id") Long id) {
        Message message = messageService.findByID(id);
        if (message == null) {
            throw new NotFoundException();
        }
        return message;
    }

    @PostMapping()
    public Message addMessage(@RequestBody Message message) {
        return messageService.create(message);
    }

    @PutMapping("{id}")
    public Message editMessage(@PathVariable("id") Long id, @RequestBody Message message) {
        return messageService.update(id, message);
    }

    @DeleteMapping("{id}")
    public void deleteMessage(@PathVariable("id") Long id) {
        messageService.delete(id);
    }
}
