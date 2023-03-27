package cho.community.factory;

import cho.community.entity.message.Message;

import static cho.community.factory.UserFactory.createUser;
import static cho.community.factory.UserFactory.createUser2;

public class CreateMessageFactory {
    public static Message createMessage() {
        return new Message("title", "content", createUser(), createUser2());
    }
}

