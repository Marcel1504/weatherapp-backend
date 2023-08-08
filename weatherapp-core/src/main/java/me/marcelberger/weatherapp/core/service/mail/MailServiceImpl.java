package me.marcelberger.weatherapp.core.service.mail;

import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendMail(String to, String subject, String text) {
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setTo(to);
            simpleMailMessage.setSubject(subject);
            simpleMailMessage.setText(text);
            mailSender.send(simpleMailMessage);
        } catch (Exception e) {
            log.warn("Could not send mail with subject '{}' to '{}': {}", subject, to, e.getMessage());
        }
    }

    @Override
    public void sendMail(String to, String subject, String text, List<String> absoluteFilePaths) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text);
            for (String path : absoluteFilePaths) {
                FileSystemResource file = new FileSystemResource(path);
                helper.addAttachment(Objects.requireNonNull(file.getFilename()), file);
            }
            mailSender.send(mimeMessage);
        } catch (Exception e) {
            log.warn("Could not send mail with subject '{}' and attachments to '{}': {}",
                    subject,
                    to,
                    e.getMessage());
        }
    }
}
