package com.cheese.springjpa.Account.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ApiModel(value = "회원 주소", description = "회원 주소")
public class Address {

    @NotEmpty
    @Column(name = "address1", nullable = false)
    @ApiModelProperty(value = "주소1", example = "address1", required = true)
    private String address1;

    @NotEmpty
    @Column(name = "address2", nullable = false)
    @ApiModelProperty(value = "주소2", example = "address2", required = true)
    private String address2;

    @NotEmpty
    @Column(name = "zip", nullable = false)
    @ApiModelProperty(value = "zip code", example = "zip code", required = true)
    private String zip;

    @Builder
    public Address(String address1, String address2, String zip) {
        this.address1 = address1;
        this.address2 = address2;
        this.zip = zip;
    }
}