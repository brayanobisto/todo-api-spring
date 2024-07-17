package com.brayanobisto.todo_api.dtos;

import com.brayanobisto.todo_api.validations.NotEmptyString;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TodoUpdateDto {
    @NotEmptyString(message = "Title cannot be empty")
    @Size(max = 255, message = "Title cannot be longer than 255 characters")
    private String title;

    private Boolean isCompleted;
}
