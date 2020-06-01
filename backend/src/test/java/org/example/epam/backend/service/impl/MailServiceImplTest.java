package org.example.epam.backend.service.impl;

import org.example.epam.backend.service.MailService;
import org.example.epam.mail.service.MailSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;

import static org.mockito.ArgumentMatchers.isA;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(locations={"classpath*:test.xml"})
public class MailServiceImplTest {

    @InjectMocks
    private MailService mailService;
    @Mock
    private MailSender emailSender;

    public MailServiceImplTest () {
        mailService = new MailServiceImpl();
    }

    @Test
    public void sendEmail() {
        mailService.sendEmail("vladquinn2010@gmail.com", "Greeting", "Hello man!");
        Mockito.verify(emailSender, Mockito.times(1))
                .sendEmail(isA(String.class), isA(String.class), isA(String.class));
    }
}