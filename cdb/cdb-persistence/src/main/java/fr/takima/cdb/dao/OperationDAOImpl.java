package fr.takima.cdb.dao;

import fr.takima.cdb.model.bean.Entity;
import fr.takima.cdb.model.bean.Operation;
import fr.takima.cdb.model.bean.Type;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class OperationDAOImpl implements OperationDAO {

    private static final Logger LOG = LoggerFactory.getLogger(OperationDAOImpl.class);
    public static final String SQL_INSERT_OPERATION = "INSERT INTO operation(entity, type, entity_id) VALUES (?, ?, ?)";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Long addOperation(Operation operation) {
        String sql = SQL_INSERT_OPERATION;
        KeyHolder keyHolder = new GeneratedKeyHolder();
        this.jdbcTemplate.update(sql, operation.getEntity(), operation.getType(), operation.getEntityId(), keyHolder);
        return keyHolder.getKey().longValue();
    }

    @Override
    public void logOperation(Entity entity, Type type, Long id) {
        String sql = SQL_INSERT_OPERATION;
        this.jdbcTemplate.update(sql, entity.toString(), type.toString(), id);
    }

    @Override
    public void logOperation(Connection connection, Entity entity, Type type, Long id) {
        String sql = SQL_INSERT_OPERATION;
        this.jdbcTemplate.update(sql, entity.toString(), type.toString(), id);
    }
}
