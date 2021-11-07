package letscode.sarafan.service;

import letscode.sarafan.domain.Comment;
import letscode.sarafan.domain.User;
import letscode.sarafan.domain.Views;
import letscode.sarafan.dto.EventsType;
import letscode.sarafan.dto.ObjectType;
import letscode.sarafan.repo.CommentRepo;
import letscode.sarafan.util.WsSender;
import org.springframework.stereotype.Service;

import java.util.function.BiConsumer;

@Service
public class CommentService {
    private final CommentRepo commentRepo;
    private final BiConsumer<EventsType, Comment> wsSender;

    public CommentService(CommentRepo commentRepo, WsSender wsSender) {
        this.commentRepo = commentRepo;
        this.wsSender = wsSender.getSender(ObjectType.COMMENT, Views.FullComment.class);
    }

    public Comment create(Comment comment, User user) {
        comment.setAuthor(user);
        Comment commentFromDb = commentRepo.save(comment);

        wsSender.accept(EventsType.CREATE, commentFromDb);

        return commentFromDb;
    }
}
