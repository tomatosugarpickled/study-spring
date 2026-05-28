package com.example.controller.controller;

import com.example.controller.domain.MemberDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

// restful(REST API)
@RestController
@RequestMapping("/api/v1/ex/**")
public class ExampleRestController {

    @GetMapping("ex01")
    public String ex01() {
        return "한동석";
    }

    @GetMapping("ex02")
    public MemberDTO ex02(){
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setId(1L);
        memberDTO.setMemberEmail("test@gmail.com");
        memberDTO.setMemberPassword("1234");
        return memberDTO;
    }

    @GetMapping("ex03")
    public List<MemberDTO> ex03(){
        List<MemberDTO> members = new ArrayList<>();
        MemberDTO memberDTO1 = new MemberDTO();
        MemberDTO memberDTO2 = new MemberDTO();

        memberDTO1.setId(1L);
        memberDTO1.setMemberEmail("test@gmail.com");
        memberDTO1.setMemberPassword("1234");

        memberDTO2.setId(2L);
        memberDTO2.setMemberEmail("test2@gmail.com");
        memberDTO2.setMemberPassword("12345");

        members.add(memberDTO1);
        members.add(memberDTO2);

        return members;
    }
}













