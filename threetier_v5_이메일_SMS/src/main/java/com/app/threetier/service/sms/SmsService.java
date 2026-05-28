package com.app.threetier.service.sms;

import com.solapi.sdk.SolapiClient;
import com.solapi.sdk.message.exception.SolapiMessageNotReceivedException;
import com.solapi.sdk.message.model.Message;
import com.solapi.sdk.message.service.DefaultMessageService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class SmsService {

    private DefaultMessageService messageService;

//    Spring이 Bean Container에 모든 객체를 등록한 후,
//    의존성 주입이 가능한 시점에서 딱 1번 실행되는 코드
//    ※ static block은 Spring이 준비도 되기 전에 실행되므로 아래 상황에서는 사용할 수 없다.
    @PostConstruct
    private void init(){
        this.messageService = SolapiClient.INSTANCE.createInstance("API 키 입력", "API 시크릿 키 입력");
    }

    public String sendSms(String phone){
        String code = createCode();
        Message message = new Message();
        message.setFrom("내 번호");
        message.setTo(phone);
        message.setText("SMS는 한글 45자, 영자 90자까지 입력할 수 있습니다.");

        try {
            messageService.send(message);
        } catch (SolapiMessageNotReceivedException exception) {
            System.out.println(exception.getFailedMessageList());
            System.out.println(exception.getMessage());
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }

        return code;
    }

    private String createCode(){
        String codes = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String code = "";
        Random random = new Random();

        for(int i = 0; i < 10; i++){
            code += codes.charAt(random.nextInt(codes.length()));
        }

        return code;
    }
}
