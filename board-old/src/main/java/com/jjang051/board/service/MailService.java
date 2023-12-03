package com.jjang051.board.service;

import com.jjang051.board.dao.MemberDao;
import com.jjang051.board.dto.LoginDto;
import com.jjang051.board.dto.MailDto;
import com.jjang051.board.dto.UpdateDto;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Slf4j
public class MailService {


    private final JavaMailSender javaMailSender;
    private final MemberDao memberDao;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    /*public void sendMail(MailDto mailDto) {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(mailDto.getReceiver());
        simpleMailMessage.setFrom("jjang051hta@naver.com");
        simpleMailMessage.setSubject(mailDto.getTitle());
        simpleMailMessage.setText(mailDto.getContent());
        javaMailSender.send(simpleMailMessage);

    }*/

    public void sendMail(MailDto mailDto) {
        MimeMessage message =  javaMailSender.createMimeMessage();

        try {
            message.setFrom("jjang051hta@naver.com");  // 보내는 사람
            message.setRecipients(MimeMessage.RecipientType.TO,mailDto.getReceiver());  // 받는 사람
            message.setSubject(mailDto.getTitle());
            message.setText(mailDto.getContent(),"UTF-8","html");
            javaMailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }


        /*SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(mailDto.getReceiver());
        simpleMailMessage.setFrom("jjang051hta@naver.com");
        simpleMailMessage.setSubject(mailDto.getTitle());
        simpleMailMessage.setText(mailDto.getContent());
        javaMailSender.send(simpleMailMessage);*/



        /*try {
            message.setFrom("jjang051hta@naver.com");  // 보내는 사람
            message.setRecipients(MimeMessage.RecipientType.TO,mailDto.getReceiver());  // 받는 사람
            message.setSubject(mailDto.getTitle());
            message.setText(mailDto.getContent(),"UTF-8","html");

            javaMailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }*/


    }

    private String randomNumber;
    public void createRandomNumber() {
        randomNumber = ""+((int)(Math.random()*90000)+10000);
        log.info("randomNumber==={}",randomNumber);
    }

    public MimeMessage createMail(String mail) {
        //렌덤 숫자 생성
        createRandomNumber();
        MimeMessage message =  javaMailSender.createMimeMessage();
        try {
            message.setFrom("jjang051hta@naver.com");  // 보내는 사람
            message.setRecipients(MimeMessage.RecipientType.TO,mail);  // 받는 사람
            message.setSubject("이메일 검증");
            String content = "<h2>요청하신 인증번호입니다.</h2>";
            content+="<h1 style='font-size:100px; color:#f00;'>"+randomNumber+"</h1>";
            message.setText(content,"UTF-8","html");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        return message;
    }
    public String sendAuthEmail(String mail) {
        MimeMessage message = createMail(mail);
        //db 비밀번호를 생성된 비밀번호르르안호화 해서 넣어둔다.
        javaMailSender.send(message);
        return randomNumber;
    }

    @Transactional
    public int sendMailAndChangePassword(UpdateDto updateDto) {
        String randomNum = sendAuthEmail(updateDto.getEmail());  // 비밀번호 보내기....
        UpdateDto dbUpdateDto = UpdateDto.builder()
                .email(updateDto.getEmail())
                .password(bCryptPasswordEncoder.encode(randomNum))
                .build();
        int result = memberDao.updatePassword(dbUpdateDto);
        return result;
    }
}
