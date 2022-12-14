package cho.community.controller.message;

import cho.community.dto.message.MessageCreateRequest;
import cho.community.entity.user.User;
import cho.community.exception.MemberNotFoundException;
import cho.community.repository.user.UserRepository;
import cho.community.response.Response;
import cho.community.service.message.MessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(value = "Messages Controller", tags = "Messages")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class MessageController {

    private final MessageService messageService;
    private final UserRepository userRepository;

    @ApiOperation(value = "편지 작성", notes = "편지 보내기")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/messages")
    public Response createMessage(@Valid @RequestBody MessageCreateRequest req) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User sender = userRepository.findByUsername(authentication.getName()).orElseThrow(MemberNotFoundException::new); //sender가 db에 있는 유저인지 확인. 없으면 throw 404
        return Response.success(messageService.createMessage(sender, req));
    }

    @ApiOperation(value = "받은 쪽지 전부 확인", notes = "받은 쪽지함 확인")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/messages/receiver")
    public Response receiveMessages() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(authentication.getName()).orElseThrow(MemberNotFoundException::new);

        return Response.success(messageService.receiveMessages(user));
    }

    @ApiOperation(value = "받은 쪽지 중 한 개 확인", notes = "받은 편지 중 하나를 확인")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/messages/receiver/{id}")
    public Response receiveMessage(@ApiParam(value = "쪽지 id", required = true) @PathVariable int id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(authentication.getName()).orElseThrow(MemberNotFoundException::new);

        return Response.success(messageService.receiveMessage(id, user));
    }

    @ApiOperation(value = "보낸 쪽지 전부 확인", notes = "보낸 쪽지함 확인")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/messages/sender")
    public Response sendMessages() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(authentication.getName()).orElseThrow(MemberNotFoundException::new);

        return Response.success(messageService.sendMessages(user));
    }

    @ApiOperation(value = "보낸 쪽지 중 한 개 확인", notes = "보낸 쪽지 중 하나를 확인")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/messages/sender/{id}")
    public Response sendMessage(@ApiParam(value = "쪽지 id", required = true) @PathVariable int id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(authentication.getName()).orElseThrow(MemberNotFoundException::new);

        return Response.success(messageService.sendMessage(id, user));
    }

    @ApiOperation(value = "받은 쪽지 삭제", notes = "받은 쪽지 삭제하기")
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/messages/receiver/{id}")
    public Response deleteReceiveMessage(@ApiParam(value = "쪽지 id", required = true) @PathVariable int id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(authentication.getName()).orElseThrow(MemberNotFoundException::new);

        messageService.deleteMessageByReceiver(id, user);
        return Response.success();
    }

    @ApiOperation(value = "보낸 쪽지 삭제", notes = "보낸 쪽지 삭제하기")
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/messages/sender/{id}")
    public Response deleteSendMessage(@ApiParam(value = "쪽지 id", required = true) @PathVariable int id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(authentication.getName()).orElseThrow(MemberNotFoundException::new);

        messageService.deleteMessageBySender(id, user);
        return Response.success();
    }

}
