package ru.diasoft.tutorial.microservice.module3.restcontroller;

import org.springframework.web.bind.annotation.*;
import ru.diasoft.tutorial.microservice.module3.exception.NotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("messages")
public class MessageController {

    private int counter = 4;
    private List<Map<String, String>> messages = new ArrayList<>();

    {
        for (int i = 1; i < counter; i++) {
            String id = String.valueOf(i);
            Map<String, String> message = new HashMap<>();
            message.put("id", id);
            message.put("text", "Message text " + id);
            messages.add(message);
        }
    }

    @GetMapping
    public List<Map<String, String>> getAllMessages() {
        return messages;
    }

    @GetMapping("{id}")
    public Map<String, String> getMessageByID(@PathVariable("id") String id) {
        return messages.stream()
                .filter(m -> m.get("id").equals(id))
                .findAny()
                .orElseThrow(NotFoundException::new);
    }

    @PostMapping()
    public Map<String, String> addMessage(@RequestBody Map<String, String> message) {
        message.put("id", String.valueOf(counter++));
        messages.add(message);
        return message;
    }

    @PutMapping("{id}")
    public Map<String, String> editMessage(@PathVariable("id") String id, @RequestBody Map<String, String> message) {
        Map<String, String> oldMessage = getMessageByID(id);
        oldMessage.putAll(message);
        oldMessage.put("id", id);
        return oldMessage;
    }

    @DeleteMapping("{id}")
    public void deleteMessage(@PathVariable("id") String id) {
        Map<String, String> message = getMessageByID(id);
        messages.remove(message);
    }
}
