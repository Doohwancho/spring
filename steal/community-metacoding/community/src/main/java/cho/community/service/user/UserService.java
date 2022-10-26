package cho.community.service.user;

import cho.community.dto.user.UserDto;
import cho.community.entity.user.Authority;
import cho.community.entity.user.User;
import cho.community.exception.MemberNotEqualsException;
import cho.community.exception.MemberNotFoundException;
import cho.community.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
//    private final BoardRepository boardRepository;
//    private final FavoriteRepository favoriteRepository;
//
    @Transactional(readOnly = true)
    public List<UserDto> findAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = new ArrayList<>();
        for (User user : users) {
            userDtos.add(UserDto.toDto(user));
        }
        return userDtos;
    }

    @Transactional(readOnly = true)
    public UserDto findUser(int id) {
        return UserDto.toDto(userRepository.findById(id).orElseThrow(MemberNotFoundException::new));
    }


    @Transactional
    public UserDto editUserInfo(int id, UserDto updateInfo) {
        User user = userRepository.findById(id).orElseThrow(() -> {
            return new MemberNotFoundException();
        });

        // 권한 처리
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!authentication.getName().equals(user.getUsername())) { //유저가 일치하는지 확인
            throw new MemberNotEqualsException();
        } else {
            user.setNickname(updateInfo.getNickname());
            user.setName(updateInfo.getName());
            return UserDto.toDto(user);
        }
    }

    @Transactional
    public void deleteUserInfo(User user, int id) {
        User target = userRepository.findById(id).orElseThrow(MemberNotFoundException::new);

        if (user.equals(target)) {
            userRepository.deleteById(id);
        } else {
            throw new MemberNotEqualsException();
        }
    }

//    @Transactional
//    public void deleteUserInfo(int id) {
//        User user = userRepository.findById(id).orElseThrow(MemberNotFoundException::new);
//
//        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        String auth = String.valueOf(authentication.getAuthorities());
//        String authByAdmin = "[" + Authority.ROLE_ADMIN +"]"; //ROLE_ADMIN마나 유저 삭제 가능
//
//
//        if(authentication.getName().equals(user.getUsername()) || auth.equals(authByAdmin)) {
//            userRepository.deleteById(id);
//        } else {
//            throw new MemberNotEqualsException();
//        }
//    }


//
//    @Transactional(readOnly = true)
//    public List<BoardSimpleDto> findFavorites(User user) {
//        List<Favorite> favorites = favoriteRepository.findAllByUser(user);
//        List<Board> boards = new ArrayList<>();
//
//        for (Favorite fav : favorites) {
//            boards.add(fav.getBoard());
//        }
//
//        List<BoardSimpleDto> boardSimpleDtoList = new ArrayList<>();
//        boards.stream().forEach(i -> boardSimpleDtoList.add(new BoardSimpleDto().toDto(i)));
//        return boardSimpleDtoList;
//    }
}
