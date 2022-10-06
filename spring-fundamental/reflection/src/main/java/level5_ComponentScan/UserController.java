package level5_ComponentScan;

public class UserController {

    @Autowired
    private UserService userService;

    public void join() {
        userService.회원가입();
    }

}