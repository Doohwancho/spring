package cho.community.repository.message;

import cho.community.entity.message.Message;
import cho.community.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {
    List<Message> findAllByReceiverAndDeletedByReceiverFalseOrderByIdDesc(User user);
    List<Message> findAllBySenderAndDeletedBySenderFalseOrderByIdDesc(User user);
}
