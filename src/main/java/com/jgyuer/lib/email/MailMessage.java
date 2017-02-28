package com.jgyuer.lib.email;

import java.util.List;

/**
 * Created by KOH on 2016/12/21.
 */
public interface MailMessage {
    void sendMail(String to, MailTemplate template);

    void sendMailBatch(List<String> to, MailTemplate template);
}
