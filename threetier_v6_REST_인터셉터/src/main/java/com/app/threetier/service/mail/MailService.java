package com.app.threetier.service.mail;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class MailService {
    private final JavaMailSender javaMailSender;

    public void sendMail(String mail, HttpServletResponse response){
        String receiver = mail;
        String sender = "tedhan.temp@gmail.com";
        String title = "인증";
        
        String code = createCode();
        Cookie cookie = new Cookie("code", code);

        cookie.setPath("/");
        cookie.setMaxAge(60 * 3); // 3분 후 코드 만료
        response.addCookie(cookie);

//        1. 코드 생성
//        2. 쿠키에 저장
//        3. 메일 전송(코드)
//        4. 링크 클릭(코드)
//        5. 컨트롤러에서 쿠키의 코드와 받은 코드 비교
//        6-1. 같으면 인증 성공
//        6-2. 다르면 인증 실패
//        6-3. 쿠키에 코드가 없으면 인증 만료

        StringBuilder body = new StringBuilder();
        body.append("<html><body>");
        body.append("<a style=\"font-size:3rem;\" href=\"http://localhost:10000/mail/confirm");
        body.append("?code=").append(code).append("\">인증 하기</a>");
        body.append("<img src=\"cid:icon\">");
        body.append("</body></html>");

        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(receiver);
            mimeMessageHelper.setSubject(title);
            mimeMessageHelper.setText(body.toString(), true);

            FileSystemResource fileSystemResource = new FileSystemResource(new File("D:\\gb_0900_hds\\html\\resource\\images\\icon1.png"));
            mimeMessageHelper.addInline("icon", fileSystemResource);

            fileSystemResource = new FileSystemResource(new File("D:\\gb_0900_hds\\spring\\memo\\spring.txt"));
            mimeMessageHelper.addAttachment("spring.txt", fileSystemResource);

            javaMailSender.send(mimeMessage);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
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















