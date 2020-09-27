package letscode.sarafan.controller;

import letscode.sarafan.exceptions.NotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("message")
public class MessageController {
    private int counter = 4;
    private List<Map<String, String>> messages = new ArrayList<>() {{
        add(new HashMap<>() {{put("id", "1"); put("text", "First message");}});
        add(new HashMap<>() {{put("id", "2"); put("text", "Second message");}});
        add(new HashMap<>() {{put("id", "3"); put("text", "Third message");}});
    }};

    @GetMapping
    public List<Map<String, String>> list() {
        return messages;
    }

    @GetMapping("{messageId}")
    public Map<String, String> getMessage(@PathVariable String messageId) {
        return getMessageById(messageId);
    }

    private Map<String, String> getMessageById(String messageId) {
        return messages.stream()
                .filter(message -> message.get("id").equals(messageId))
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }

    @PostMapping
    public Map<String, String> create(@RequestBody Map<String, String> message) {
        message.put("id", String.valueOf(counter++));
        messages.add(message);
        return message;
    }

    @PutMapping("{messageId}")
    public Map<String, String> update(
            @PathVariable String messageId,
            @RequestBody Map<String, String> message)
    {
        Map<String, String> messageFormDb = getMessageById(messageId);
        messageFormDb.putAll(message);
        messageFormDb.put("id", messageId);
        return messageFormDb;
    }

    @DeleteMapping("{messageId}")
    public void delete(@PathVariable String messageId) {
        Map<String, String> messageFromDb = getMessageById(messageId);
        messages.remove(messageFromDb);
    }

}
