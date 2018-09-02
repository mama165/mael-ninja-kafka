package fr.takima.cdb.dao;

import fr.takima.cdb.model.Entity;
import fr.takima.cdb.model.Operation;
import fr.takima.cdb.model.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class OperationDAOImpl implements OperationDAO {

    private static final Logger LOG = LoggerFactory.getLogger(OperationDAOImpl.class);
    public static final String SQL_INSERT_OPERATION= "INSERT INTO operation(entity, type, entity_id) VALUES (?, ?, ?)";

    @Override
    public Long addOperation(Operation operation) {
        return null;
    }

    @Override
    public void logOperation(Entity entity, Type type, Long id) {

    }

    @Override
    public void logOperation(Connection connection, Entity entity, Type type, Long id) {

    }
}
