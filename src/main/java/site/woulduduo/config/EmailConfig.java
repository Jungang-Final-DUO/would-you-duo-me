package site.woulduduo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class EmailConfig {

    @Value("${spring.mail.host}")
    private String host;

    @Value("${spring.mail.port}")
    private int port;

    @Value("${spring.mail.username}")
    private String username;

    @Value("${spring.mail.password}")
    private String password;

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host); // SMTP 호스트 설정
        mailSender.setPort(port); // SMTP 포트 설정
        mailSender.setUsername(username); // 계정 이메일 주소
        mailSender.setPassword(password); // 계정 앱 비밀번호

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.smtp.starttls.enable", "true"); // STARTTLS 사용 설정
        props.put("mail.smtp.auth", "true"); // SMTP 인증 설정
        props.put("mail.smtp.timeout", "1800000");

        return mailSender;
    }

}

