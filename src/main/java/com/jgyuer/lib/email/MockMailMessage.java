package com.jgyuer.lib.email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by KOH on 2016/12/21.
 */
public class MockMailMessage implements MailMessage {
    private final static Logger logger = LoggerFactory.getLogger(MailMessage.class);

    @Override
    public void sendMail(String to, MailTemplate template) {
        logger.info(template.toString());
    }

    @Override
    public void sendMailBatch(List<String> to, MailTemplate template) {

    }
}
