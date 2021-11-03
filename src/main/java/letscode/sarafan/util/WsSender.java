package letscode.sarafan.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import letscode.sarafan.dto.EventsType;
import letscode.sarafan.dto.ObjectType;
import letscode.sarafan.dto.WsEventDto;
import lombok.AllArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.util.function.BiConsumer;

@Component
@AllArgsConstructor
public class WsSender {
    private final SimpMessagingTemplate template;
    private final ObjectMapper mapper;

    public <T> BiConsumer<EventsType, T> getSender(ObjectType objectType, Class view) {
        ObjectWriter writer = mapper
                .setConfig(mapper.getSerializationConfig())
                .writerWithView(view);
        return (EventsType eventsType, T payload) -> {
            String value = null;
            try {
                value = writer.writeValueAsString(payload);
                template.convertAndSend(
                        "/topic/activity",
                        new WsEventDto(objectType, eventsType, value));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        };
    }

}
