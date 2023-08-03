package com.test.cafekiosk.spring;

import com.test.cafekiosk.spring.client.mail.MailSendClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public abstract class IntegrationTestSupport {

    /**
     * 통합 클래스를 만들어서 상속 통해서 환경을 통일시킬 때, MockBean 처리를 하면 원하는 빈을 모의 객체로 넣어주기 때문에 다른 환경으로 본다.
     * MockBean 을 상위 클래스에 모으거나 아예 상위 클래스를 두 개로 분리하는 방법이 있다.
     */
    @MockBean
    protected MailSendClient mailSendClient;
}
