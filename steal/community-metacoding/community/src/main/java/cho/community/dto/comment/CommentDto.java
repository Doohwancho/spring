package cho.community.dto.comment;

import cho.community.dto.user.UserDto;
import cho.community.entity.comment.Comment;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {

    private int id;
    private String content;
    private UserDto userDto;
    private LocalDate createdAt;

    public CommentDto toDto(Comment comment) {
        return new CommentDto(
                comment.getId(),
                comment.getContent(),
                UserDto.toDto(comment.getUser()),
                comment.getCreateDate()
        );
    }
}