package com.cho.annotation.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embedded;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    String username;

    @Embedded
    Password password;

    @Embedded
    Address address;
}
