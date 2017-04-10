package com.ideabox.tests.models;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.Sets;

import java.util.LinkedHashMap;
import java.util.Set;


/**
 * Prepares the request payload JSON using the parameters and a template.
 * Utilizes {@link VelocityTemplateService} to produce the actual JSON string.
 */
public class RequestPayload extends EntityMapAdapter {

    private final String jsonTemplatePath;
    private final Set<String> nullKeys = Sets.newLinkedHashSet();

    public RequestPayload(String jsonTemplatePath ) {
        super(new LinkedHashMap<String, Object>(), jsonTemplatePath);

        Preconditions.checkArgument(!Strings.isNullOrEmpty(jsonTemplatePath));

        this.jsonTemplatePath = jsonTemplatePath;
    }

    public RequestPayload parameter(String key, Object value) {
        getEntityMap().put(key, value);

        if (value == null) {
            this.nullKeys.add(key);
        }
        else {
            if (this.nullKeys.contains(key)) {
                this.nullKeys.remove(key);
            }
        }

        return this;
    }

    @Override
    public String toString() {
        String payload = super.toString();
        payload = replaceNullTags(payload);
        return payload;
    }

    /**
     * Replaces tags of null parameters in the template with "null".
     * Velocity ignores null values, so we must set them to null manually
     */
    private String replaceNullTags(String payload) {
        if (this.nullKeys.isEmpty()) {
            return payload;
        }

        for (String key : this.nullKeys) {
            String tag = "${" + key + "}";
            String quotedTag = "\"" + tag + "\"";

            // replace quoted tags
            payload = payload.replace(quotedTag, "null");

            // replace number and boolean tags - without quotes
            payload = payload.replace(tag, "null");
        }

        return payload;
    }

}
