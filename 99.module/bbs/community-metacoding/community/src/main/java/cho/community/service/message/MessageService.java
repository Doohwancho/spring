package cho.community.service.message;

import cho.community.dto.message.MessageCreateRequest;
import cho.community.dto.message.MessageDto;
import cho.community.entity.message.Message;
import cho.community.entity.user.User;
import cho.community.exception.MemberNotEqualsException;
import cho.community.exception.MemberNotFoundException;
import cho.community.exception.MessageNotFoundException;
import cho.community.repository.message.MessageRepository;
import cho.community.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MessageService {
    private Logger log = LogManager.getLogger(MessageService.class);

    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    @Transactional
    public MessageDto createMessage(User sender, MessageCreateRequest req) {
        log.debug(sender.toString());
        log.debug(req.toString());

        User receiver = userRepository.findByNickname(req.getReceiverNickname()).orElseThrow(MemberNotFoundException::new);

        log.debug(receiver.toString());

        Message message = new Message(req.getTitle(), req.getContent(), sender, receiver);
        return MessageDto.toDto(messageRepository.save(message));
    }

    @Transactional(readOnly = true)
    public List<MessageDto> receiveMessages(User user) {

        List<MessageDto> messageDtoList = new ArrayList<>();
        List<Message> messageList = messageRepository.findAllByReceiverAndDeletedByReceiverFalseOrderByIdDesc(user);

        for (Message message : messageList) {
            messageDtoList.add(MessageDto.toDto(message));
        }
        return messageDtoList;
    }


    @Transactional(readOnly = true)
    public MessageDto receiveMessage(int id, User user) {
        Message message = messageRepository.findById(id).orElseThrow(MessageNotFoundException::new);

        if (message.getReceiver() != user) {
            throw new MemberNotEqualsException();
        }
        if (message.isDeletedByReceiver()) {
            throw new MessageNotFoundException();
        }
        return MessageDto.toDto(message);
    }

    @Transactional(readOnly = true)
    public List<MessageDto> sendMessages(User user) {
        List<MessageDto> messageDtoList = new ArrayList<>();
        List<Message> messageList = messageRepository.findAllBySenderAndDeletedBySenderFalseOrderByIdDesc(user);

        for (Message message : messageList) {
            messageDtoList.add(MessageDto.toDto(message));
        }
        return messageDtoList;
    }

    @Transactional(readOnly = true)
    public MessageDto sendMessage(int id, User user) {
        Message message = messageRepository.findById(id).orElseThrow(MessageNotFoundException::new);

        if (message.getSender() != user) {
            throw new MemberNotEqualsException();
        }

        if (message.isDeletedByReceiver()) {
            throw new MessageNotFoundException();
        }
        return MessageDto.toDto(message);
    }

    @Transactional
    public void deleteMessageByReceiver(int id, User user) {
        Message message = messageRepository.findById(id).orElseThrow(MessageNotFoundException::new);

        if (message.getReceiver() == user) {
            message.deleteByReceiver();
        } else {
            throw new MemberNotEqualsException();
        }

        if (message.isDeletedMessage()) {
            // 수신, 송신자 둘다 삭제할 경우
            messageRepository.delete(message);
        }
    }

    @Transactional
    public void deleteMessageBySender(int id, User user) {
        Message message = messageRepository.findById(id).orElseThrow(MessageNotFoundException::new);

        if (message.getSender() == user) {
            message.deleteBySender();
        } else {
            throw new MemberNotEqualsException();
        }

        if (message.isDeletedMessage()) {
            messageRepository.delete(message);
        }
    }
}
