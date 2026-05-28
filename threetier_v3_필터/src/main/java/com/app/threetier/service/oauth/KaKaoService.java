package com.app.threetier.service.oauth;

import com.app.threetier.common.exception.LoginFailException;
import com.app.threetier.dto.MemberDTO;
import com.app.threetier.repository.MemberDAO;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class KaKaoService {
    private final MemberDAO memberDAO;

//    화면(login/event.js)에서 REST API KEY를 전달하여 code를 받는다.
//    redirect_uri는 /kakao/login이고, 이 컨트롤러에서 아래의 메소드를 사용한다.
    public MemberDTO kakaoLogin(String code){
        String accessToken = null;
        String requestURI = "https://kauth.kakao.com/oauth/token";

        URL url = null;
        try {
//            URI을 담고
            url = new URL(requestURI);
//            요청 준비를 한다.
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            전달할 파라미터를 준비한다.
            StringBuilder stringBuilder = new StringBuilder();
//            준비한 파라미터를 해당 경로로 보낸다.
            BufferedWriter bufferedWriter = null;

//            POST로 전송한다.
            connection.setRequestMethod("POST");
//            데이터를 보내기 위해서는 setDoOutput()에 true를 전달해야 한다.
            connection.setDoOutput(true);

//            파라미터를 구성한다(ref. kakao rest api login documentation)
            stringBuilder.append("grant_type=authorization_code");
            stringBuilder.append("&client_id=6c9664c00ac5573fa3d8f1caf80e67f3");
            stringBuilder.append("&redirect_uri=http://localhost:10000/kakao/login");
            stringBuilder.append("&code=").append(code);

//            등록한 URI로 파라미터를 전송하기 위해서는
//            열어놓았던 Connection 객체에 있는 Writer를 사용해야 한다.
//            bufferedWriter의 destination(목적지)은 등록한 URI이다.

            bufferedWriter = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));

//            파라미터 전체를 버퍼에 내보낸다.
            bufferedWriter.write(stringBuilder.toString());

//            버퍼에 있던 파라미터들이 destination(URI)로 이동한다.
            bufferedWriter.close();
            
//            200: 요청 성공
            if(connection.getResponseCode() == 200){
//                응답 데이터를 불러올 준비를 한다.
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line = null;
                String result = "";

//                문서 끝까지 다 읽은 다음
                while((line = bufferedReader.readLine()) != null){
//                    한 줄씩 result에 누적해서 연결한다.
                    result += line;
                }

                bufferedReader.close();

//                result는 JSON 문자열이다.
//                따라서 Java 객체로 변환해야 하며, 이 때 gson 라이브러리를 사용한다.
                JsonElement jsonElement = JsonParser.parseString(result);
//                accessToken을 가져온다.
                accessToken = jsonElement.getAsJsonObject().get("access_token").getAsString();
            }

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

//        정보를 요청하기 위해서 token을 전달한다.
        return getKakaoInfo(accessToken);
    }

//    code로 요청해서 응답받은 access_token을 매개변수로 받는다.
    public MemberDTO getKakaoInfo(String token) {
        String requestURI = "https://kapi.kakao.com/v2/user/me";
        MemberDTO kakaoInfo = null;

        URL url = null;
        try {
            url = new URL(requestURI);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            StringBuilder stringBuilder = new StringBuilder();
            BufferedWriter bufferedWriter = null;

            connection.setRequestMethod("POST");
//            토큰은 인증을 위한 정보라서, 일반 헤더의 바디에 전달할 수 없다.
            connection.setRequestProperty("Authorization", "Bearer " + token);

//            200: 요청 성공
            if(connection.getResponseCode() == 200){
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line = null;
                String result = "";

                while((line = bufferedReader.readLine()) != null){
                    result += line;
                }

                bufferedReader.close();

//                내가 필요한 정보를 쏙쏙 가져온다.
                JsonElement jsonElement = JsonParser.parseString(result);
                JsonObject kakaoAccount = jsonElement.getAsJsonObject().get("kakao_account").getAsJsonObject();
                JsonElement profile = kakaoAccount.getAsJsonObject().get("profile");

                String memberEmail = kakaoAccount.getAsJsonObject().get("email").getAsString();
                String profileImageUrl = profile.getAsJsonObject().get("profile_image_url").getAsString();
                String memberName = profile.getAsJsonObject().get("nickname").getAsString();

                kakaoInfo = new MemberDTO();
                kakaoInfo.setMemberName(memberName);
                kakaoInfo.setMemberEmail(memberEmail);
                kakaoInfo.setProfileImageUrl(profileImageUrl);

//                카카오에서 받은 이메일을 우리 DB에서 검사해본다.
                Optional<MemberDTO> foundMember = memberDAO.findByMemberEmail(kakaoInfo.getMemberEmail());

//                카카오 정보가 우리 DB에 없으면,
                if(foundMember.isEmpty()){
//                    최초 로그인
//                    1) 추가 정보 입력 페이지로 이동
                    return kakaoInfo;
//                    2) 바로 메인 페이지로 이동
//                    DB INSERT

                }else{ // 우리 DB에 있네?
//                    기존 회원
                    return foundMember.get();
                }
            }

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
}











