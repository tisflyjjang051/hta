package com.jjang051.board.controller;

import com.jjang051.board.dto.CalendarDto;
import com.jjang051.board.dto.LoginDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/calendar")
public class CalendarController {
    @GetMapping("/")
    public String calendar() {
        return "/calendar/calendar";
    }

    @GetMapping("/list")
    @ResponseBody
    public Map<String, List<CalendarDto>> calendarList() {
        Map<String, List<CalendarDto>> resultMap = new HashMap<>();
        List<CalendarDto> calendarDtoList = new ArrayList<>();
        calendarDtoList.add(CalendarDto.builder().title("하루종일 미팅").start("2023-11-12").url("http://www.daum.net").duration("02:00").build());
        calendarDtoList.add(CalendarDto.builder().title("aaa").start("2023-11-12T10:30:00").end("2023-11-12T12:30:00").url("http://www.daum.net").build());
        calendarDtoList.add(CalendarDto.builder().title("aaa").start("2023-11-12T13:30:00").end("2023-11-12T14:30:00").url("http://www.daum.net").build());
        calendarDtoList.add(CalendarDto.builder().title("aaa").start("2023-11-12T15:30:00").end("2023-11-12T16:30:00").url("http://www.daum.net").build());
        calendarDtoList.add(CalendarDto.builder().title("aaa").start("2023-11-12T17:30:00").end("2023-11-12T17:50:00").url("http://www.daum.net").build());




        calendarDtoList.add(CalendarDto.builder().title("bbb").start("2023-11-24").build());
        calendarDtoList.add(CalendarDto.builder().title("ccc").start("2023-11-26").end("2023-11-18").build());
        resultMap.put("calendarDtoList",calendarDtoList);
        return resultMap;
    }



    @GetMapping("/list02")
    @ResponseBody
    public List<CalendarDto> calendarList02() {
        List<CalendarDto> calendarDtoList = new ArrayList<>();
        calendarDtoList.add(CalendarDto.builder().title("하루종일 미팅").start("2023-11-12").url("http://www.daum.net").duration("02:00").build());
        calendarDtoList.add(CalendarDto.builder().title("aaa").start("2023-11-12T10:30:00").end("2023-11-12T12:30:00").url("http://www.daum.net").build());
        calendarDtoList.add(CalendarDto.builder().title("aaa").start("2023-11-12T13:30:00").end("2023-11-12T14:30:00").url("http://www.daum.net").build());
        calendarDtoList.add(CalendarDto.builder().title("aaa").start("2023-11-12T15:30:00").end("2023-11-12T16:30:00").url("http://www.daum.net").build());
        calendarDtoList.add(CalendarDto.builder().title("aaa").start("2023-11-12T17:30:00").end("2023-11-12T17:50:00").url("http://www.daum.net").build());

        return calendarDtoList;
    }

}
