package com.cheese.springjpa.Account.api;

import com.cheese.springjpa.Account.application.AccountService;
import com.cheese.springjpa.Account.dao.AccountSearchService;
import com.cheese.springjpa.Account.dto.AccountDto;
import com.cheese.springjpa.Account.dto.AccountSearchType;
import com.cheese.springjpa.common.model.PageRequest;
import io.swagger.annotations.*;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("accounts")
@Api("Account Controller API V1")
public class AccountController {

    private final AccountService accountService;
    private final AccountSearchService accountSearchService;

    public AccountController(AccountService accountService, AccountSearchService accountSearchService) {
        this.accountService = accountService;
        this.accountSearchService = accountSearchService;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    @ApiOperation(value="회원가입"
            ,notes = "유저 회원가입"
            //,response = TemplateEntity.class
    )
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
    public AccountDto.Res signUp(@RequestBody @Valid @ApiParam(value = "회원 한 명의 정보를 갖는 객체", required = false) final AccountDto.SignUpReq dto) {
        return new AccountDto.Res(accountService.create(dto));
    }

    @GetMapping
    public Page<AccountDto.Res> getAccounts(
            @RequestParam(name = "type") final AccountSearchType type,
            @RequestParam(name = "value", required = false) final String value,
            final PageRequest pageRequest
    ) {
        return accountSearchService.search(type, value, pageRequest.of()).map(AccountDto.Res::new);
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
