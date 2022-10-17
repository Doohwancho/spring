package com.cheese.swagger.account;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("accounts")
@Api("Account Controller API V1")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
//    @ApiOperation(value="회원가입"
//            ,notes = "유저 회원가입"
//            //,response = TemplateEntity.class
//    )
//    @ApiImplicitParams(
////        case1) 단일 dto에 여러 fields에 매핑은 안되고, 여러 wrapper class 파라미터에 매핑하는 방식은 됨.
////      value = {
////            @ApiImplicitParam(name= "email", value= "doohwancho1993@gmail.com", example= "이메일 주소"),
////            @ApiImplicitParam(name= "firstName",value= "doohwan",example= "이름"),
////            @ApiImplicitParam(name= "lastName",value= "cho",example= "성"),
////            @ApiImplicitParam(name= "password",value= "1234",example= "비밀번호"),
////            @ApiImplicitParam(name= "address1",value= "gyunggido",example= "주소1"),
////            @ApiImplicitParam(name= "address2",value= "gimposi",example= "주소2"),
////            @ApiImplicitParam(name= "zip",value= "10101",example= "zip code")
////        }
//
//            //case2) 단일 dto에 여러 파라미터에 넣는 방식인데, not recommended. DTO에 @ApiModelProperty() 방법이 더 elegant.
//            @ApiImplicitParam(name = "dto",
//                    value =
//                            "{\n" +
//                                    "\"email\": \"doohwancho1993@gmail.com\",\n" +
//                                    "\"firstName\":\"doohwan\",\n"+
//                                    "\"lastName\":\"cho\",\n"+
//                                    "\"password\":\"1234\",\n"+
//                                    "\"address1\":\"gyung-gi\",\n"+
//                                    "\"address2\":\"gimpo\",\n"+
//                                    "\"zip\":\"10101\"" +
//                                    "\n}",
//                    required = true,
//                    dataTypeClass = AccountDto.SignUpReq.class,
//                    paramType = "application/json")
//    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            code = 400,
                            message = "NiFi was unable to complete the request because it was invalid. The request should not be retried without modification."
                            //, response = MediaDataProductResponseV2.class -> 특정 response class 만들어서 반환도 가능하다.
                    ),
                    @ApiResponse(code = 401, message = "Client could not be authenticated."),
                    @ApiResponse(code = 403, message = "Client is not authorized to make this request."),
                    @ApiResponse(code = 409, message = "The request was valid but NiFi was not in the appropriate state to process it. Retrying the same request later may be successful.")
            }
    )
    public AccountDto.Res signUp(@RequestBody @ApiParam(value = "회원 한 명의 정보를 갖는 객체", required = false) final AccountDto.SignUpReq dto) {
        return new AccountDto.Res(accountService.create(dto));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public AccountDto.Res getUser(@PathVariable final long id) {
        return new AccountDto.Res(accountService.findById(id));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public AccountDto.Res updateMyAccount(@PathVariable final long id, @RequestBody final AccountDto.MyAccountReq dto) {
        return new AccountDto.Res(accountService.updateMyAccount(id, dto));
    }
}
