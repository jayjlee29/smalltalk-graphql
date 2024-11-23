package com.tenwell.smalltalk.data;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@Builder
public class Profile {

    private String userId;
    private String userName;

    protected Profile() {

    }
    protected Profile(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    

}
