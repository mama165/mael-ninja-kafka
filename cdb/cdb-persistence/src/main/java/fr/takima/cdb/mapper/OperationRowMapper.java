package fr.takima.cdb.mapper;

import fr.takima.cdb.model.bean.Operation;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OperationRowMapper implements RowMapper<Operation> {
    @Override
    public Operation mapRow(ResultSet row, int i) throws SQLException {
        Operation operation = new Operation(row.getLong("id"), row.getString("entity"), row.getString("type"), row.getLong("entity_id"));
        return operation;
    }
}
