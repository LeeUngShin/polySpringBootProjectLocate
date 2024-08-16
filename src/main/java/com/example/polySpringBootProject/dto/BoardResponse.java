package com.example.polySpringBootProject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardResponse {
    boolean success;
    Long boardId;
    String message;

    public boolean isSuccess() {
        return success;
    }
}
