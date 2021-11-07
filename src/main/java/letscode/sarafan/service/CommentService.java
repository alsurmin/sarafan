package letscode.sarafan.service;

import letscode.sarafan.domain.Comment;
import letscode.sarafan.domain.User;
import letscode.sarafan.repo.CommentRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CommentService {
    private final CommentRepo commentRepo;

    public Comment create(Comment comment, User user) {
        comment.setAuthor(user);
        commentRepo.save(comment);

        return comment;
    }
}
