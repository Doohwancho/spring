package com.practice.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JoinDto {
//    @NotNull
    private String username;

//    @NotNull
    private String password;
    private String intro;
}