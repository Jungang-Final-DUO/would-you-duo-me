package site.woulduduo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class EmailConfig {

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com"); // SMTP 호스트 설정
        mailSender.setPort(587); // SMTP 포트 설정
        mailSender.setUsername("nuhgnoesoj@gmail.com"); // 계정 이메일 주소
        mailSender.setPassword("ndwsffwphbglwkcz"); // 계정 앱 비밀번호

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.smtp.starttls.enable", "true"); // STARTTLS 사용 설정
        props.put("mail.smtp.auth", "true"); // SMTP 인증 설정

        return mailSender;
    }

}

