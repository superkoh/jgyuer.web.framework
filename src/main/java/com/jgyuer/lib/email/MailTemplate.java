package com.jgyuer.lib.email;

import com.jgyuer.framework.lang.BaseObject;

/**
 * Created by KOH on 2016/12/21.
 */
public class MailTemplate extends BaseObject {
    private String templateId;
    private String title;
    private String content;

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
