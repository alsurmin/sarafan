package letscode.sarafan.repo;

import io.sentry.spring.tracing.SentrySpan;
import letscode.sarafan.domain.Message;
import letscode.sarafan.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepo extends JpaRepository<Message, Long> {
    @EntityGraph(attributePaths = { "comments" })
    @SentrySpan
    Page<Message> findByAuthorIn(List<User> users, Pageable pagable);
}
