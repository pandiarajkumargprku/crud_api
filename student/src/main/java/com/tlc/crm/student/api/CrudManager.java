package com.tlc.crm.student.api;

import com.tlc.commons.code.ErrorCode;

import com.tlc.crm.common.config.AuditEntry;
import com.tlc.crm.common.config.ConfigManager;
import com.tlc.crm.student.status.StudentErrorCode;

import com.tlc.sql.SQLAccess;
import com.tlc.sql.api.DataContainer;
import com.tlc.sql.api.Row;
import com.tlc.sql.api.dml.*;
import com.tlc.sql.api.ds.OrgDataStore;

import java.util.*;

/**
 * Implements crud operation
 *
 * @author PandiarajkumarG
 */
public class CrudManager implements ConfigManager<StudentDetails> {
    private static final Table TABLE = Table.get("Student");
    private static final OrgDataStore ORG_DATA_STORE = getOrgDataStore();

    /**
     * Creates the instance
     */
    private static class Instance {
        private static final CrudManager INSTANCE = new CrudManager();
    }

    private CrudManager() {
    }

    public static CrudManager getInstance() {
        return Instance.INSTANCE;
    }

    /**
     * Gets Org datastore id
     */
    private static OrgDataStore getOrgDataStore() {
        return SQLAccess.get().getOrgDataStore(1L);
    }

    /**
     * Insert  into database
     *
     * @param model
     */
    @Override
    public void create(final StudentDetails model) {
    }

    /**
     * Collection of data is inserted to database
     *
     * @param model
     */
    @Override
    public void create(final Collection<StudentDetails> model) {
        final DataContainer dataContainer = DataContainer.create();

        for (final StudentDetails studentList : model) {
            final Row row = new Row(TABLE);

            row.set("EMAIL", studentList.getEmail());
            row.set("NAME", studentList.getName());
            dataContainer.addNewRow(row);
            ORG_DATA_STORE.commitChanges(dataContainer);
        }
    }

    /**
     * Updates data in table.
     *
     * @param model
     */
    @Override
    public void update(final StudentDetails model) {
        final Row row = new Row(TABLE, model.getId());
        final DataContainer dataContainer = DataContainer.create();

        if (exists(model)) {
            setColumns(row, model);
            dataContainer.updateRow(row);
            ORG_DATA_STORE.commitChanges(dataContainer);
        } else {
            throw ErrorCode.get(StudentErrorCode.ID_NOT_FOUND);
        }
    }

    /**
     * Sets the column values.
     *
     * @param row
     * @param model
     */
    private void setColumns(final Row row, final StudentDetails model) {
        row.set("EMAIL", model.getEmail());
        row.set("NAME", model.getName());
    }

    /**
     * Deletes the data.
     *
     * @param model
     */
    @Override
    public void delete(final StudentDetails model) {
        final Collection<StudentDetails> student = new ArrayList<>();

        student.add(model);

        if (exists(model)) {
            delete(student);
        } else {
            throw ErrorCode.get(StudentErrorCode.ID_NOT_FOUND);
        }
    }

    /**
     * Checks element for existence.
     *
     * @param model
     */
    @Override
    public boolean exists(final StudentDetails model) {
        return (ORG_DATA_STORE.get(TABLE, model.getId()) != null) ? true : false;
    }

    /**
     * Deletes collection of data.
     *
     * @param models
     */
    @Override
    public void delete(final Collection<StudentDetails> models) {
        final Collection<Long> ids = new ArrayList<>();

        models.forEach(students -> ids.add(students.getId()));
        ORG_DATA_STORE.delete(TABLE, ids);
    }

    /**
     * Partial get using id
     *
     * @param id
     */
    @Override
    public StudentDetails partialGet(final Long id) {
        final StudentDetails details = new StudentDetails();

        details.setId(id);
        return details;
    }

    /**
     * Retrieves the data from database.
     *
     * @param id
     */
    @Override
    public StudentDetails get(final Long id) {
        final Row row = ORG_DATA_STORE.get(TABLE, id);
        final StudentDetails details = new StudentDetails();

        details.setId(row.get("ID"));
        details.setEmail(row.get("EMAIL"));
        details.setName(row.get("NAME"));
        return details;
    }

    /**
     * Retrieves the collection of data from database.
     *
     * @param id
     */
    @Override
    public Collection<StudentDetails> get(final Collection<Long> id) {
        return null;
    }

    @Override
    public AuditEntry auditEntry(StudentDetails model) {
        return null;
    }
}


