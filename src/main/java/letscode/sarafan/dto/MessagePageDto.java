package letscode.sarafan.dto;

import com.fasterxml.jackson.annotation.JsonView;
import letscode.sarafan.domain.Message;
import letscode.sarafan.domain.Views;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@JsonView(Views.FullMessage.class)
public class MessagePageDto {
    private List<Message> messages;
    private int currentPage;
    private int totalPages;
}
