package com.ideabox.tests.models;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

import java.util.Map;

/**
 * Created by spavlov on 09.09.16.
 */

public class EntityMapAdapter {

    private final Map<String, Object> entityMap;
	private final String jsonTemplatePath;
	private final VelocityTemplateService templateService;

    public EntityMapAdapter(Map<String, Object> entityMap, String jsonTemplatePath) {
        Preconditions.checkNotNull(entityMap);
		Preconditions.checkArgument(!Strings.isNullOrEmpty(jsonTemplatePath));
		this.jsonTemplatePath = jsonTemplatePath;
        this.entityMap = entityMap;
		this.templateService = new VelocityTemplateService();
    }

	@Override
	public String toString() {
		String payload = null;
		try {
			payload = getTemplateService().fillTemplate(getJsonTemplatePath(), getEntityMap());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return payload;
	}



    protected Map<String, Object> getEntityMap() {
        return this.entityMap;
    }

    public Object getParameter(String key) {
        return this.entityMap.get(key);
    }

	public String getJsonTemplatePath() {
		return jsonTemplatePath;
	}

	public VelocityTemplateService getTemplateService() {
		return templateService;
	}
}
