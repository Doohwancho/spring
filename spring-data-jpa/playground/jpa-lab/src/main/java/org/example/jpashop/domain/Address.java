package org.example.jpashop.domain;

import javax.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable //Address가 필드 타입으로 들어가는데, primitive type, wrapper type외에 jpa가 알아들을 수 있게 마크해 놓는 것
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) //TODO - Q. why Protected? -> A. safer. 무분별한 객체 생성에 대해 한번 더 체크할 수 있는 수단. attribute 3개 중, 2개만 채우고 생성하는 것 등 막음.
@AllArgsConstructor
public class Address {

    private String city;
    private String street;
    private String zipcode;
}
