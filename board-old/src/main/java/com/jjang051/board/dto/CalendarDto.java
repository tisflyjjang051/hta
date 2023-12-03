package com.jjang051.board.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CalendarDto {
    private String title;
    private String start;
    private String end;
    private String url;
    private String duration;

}
