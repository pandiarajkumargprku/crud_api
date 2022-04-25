package com.tlc.crm.student.action;

import com.tlc.commons.json.Json;
import com.tlc.commons.json.JsonArray;
import com.tlc.commons.json.JsonObject;

import com.tlc.crm.common.action.secure.CrmConfigAction;
import com.tlc.crm.common.config.ConfigManager;
import com.tlc.crm.student.api.CrudManager;
import com.tlc.crm.student.api.StudentDetails;
import com.tlc.crm.student.validator.HibernateValidation;

import com.tlc.validator.type.Group.Create;
import com.tlc.validator.type.Group.Update;
import com.tlc.web.WebAction;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Data from API is analysed and respective operation is performed.
 *
 * @author PandiarajkumarG
 */
@WebAction(path = "/student/mgmt")
public class StudentApi extends CrmConfigAction<StudentDetails> {

    /**
     * Get Config Manager
     *
     * @return
     */
    @Override
    public ConfigManager<StudentDetails> getConfigManager() {
        return CrudManager.getInstance();
    }

    /**
     * Inserts the details using json object
     *
     * @param jsonObject
     */
    @Override
    public StudentDetails construct(final JsonObject jsonObject) {
        final Long id = jsonObject.optLong("id", 0);
        final String email = jsonObject.optString("email", null);
        final String name = jsonObject.optString("name", null);
        final String type = jsonObject.getString("data");
        final StudentDetails details = new StudentDetails(id, email, name);

        if (type.equals("create") || type.equals("update")) {
            HibernateValidation.validate(details, getGroups(type));
        }
        return details;
    }

    /**
     * Get and checks group of class.
     *
     * @param type
     */
    private Class getGroups(final String type) {

        if (type.equals("create")) {
            return Create.class;
        }
        return Update.class;
    }

    /**
     * Constructs Json object from student data.
     *
     * @param model
     */
    @Override
    public JsonObject construct(final StudentDetails model) {
        final JsonObject jsonObject = Json.object();

        jsonObject.put("id", model.id());
        jsonObject.put("email", model.getEmail());
        jsonObject.put("name", model.getName());
        return jsonObject;
    }

    /**
     * Iterates Json object and return collection of data.
     *
     * @param data
     */
    @Override
    public Collection<StudentDetails> constructArray(final JsonObject data) {
        final Collection<StudentDetails> studentList = new ArrayList<>();
        final JsonArray dataArray = data.getJsonArray("data");
        final String type = data.getString("type");

        for (int index = 0; index < dataArray.size(); index++) {
            final JsonObject dataObject = dataArray.getJsonObject(index);
            final Long id = dataObject.optLong("id", 0);
            final String email = dataObject.optString("email", null);
            final String name = dataObject.optString("name", null);
            final StudentDetails details = new StudentDetails(id, email, name);

            if (type.equals("create") || type.equals("update")) {
                try {
                    HibernateValidation.validate(details, getGroups(type));
                    studentList.add(details);
                } catch (Exception exception) {
                    System.out.println(exception);
                }
            }
        }
        return studentList;
    }
}