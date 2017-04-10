package com.ideabox.tests.models;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.StringWriter;
import java.util.Map;

/**
 * Created by spavlov on 09.09.16.
 */
public class VelocityTemplateService {

    private VelocityEngine velocityEngine = new VelocityEngine();

    public String fillTemplate(String templateLocation, Map<String, Object> templateParameters) throws Exception {
        VelocityContext context = new VelocityContext(templateParameters);

        StringWriter messageTextWriter = new StringWriter();
        Template template = this.velocityEngine.getTemplate(templateLocation);
        template.merge(context, messageTextWriter);

        return messageTextWriter.toString();
    }

    public void setVelocityEngine(VelocityEngine velocityEngine) {
        this.velocityEngine = velocityEngine;
    }
}
