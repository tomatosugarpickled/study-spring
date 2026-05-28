package com.app.threetier.controller.sms;

import com.app.threetier.dto.MemberDTO;
import com.app.threetier.service.sms.SmsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@Slf4j
public class SmsController {
    private final SmsService smsService;

    @GetMapping("/message/send")
    public String send(){
        return "/sms/sms";
    }

//    @RequestMapping(value = "send",
//            method = RequestMethod.POST,
//            consumes = MediaType.APPLICATION_JSON_VALUE,
//            produces = MediaType.TEXT_PLAIN_VALUE)
    @PostMapping("/api/messages/send")
//    (1)
//    @ResponseBody
//    public String sendSms(@RequestBody String phone){
//        log.info("{}.............", phone);
//        return smsService.sendSms(phone);
//    }

//    (2)
    @ResponseBody
    public String sendSms(@RequestBody MemberDTO memberDTO){
        log.info("{}.............", memberDTO.getPhone());
        return smsService.sendSms(memberDTO.getPhone());
    }
}














