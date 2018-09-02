package fr.takima.cdb.dao;

import fr.takima.cdb.model.Entity;
import fr.takima.cdb.model.Operation;
import fr.takima.cdb.model.Type;
import org.springframework.stereotype.Repository;

import java.sql.Connection;

@Repository
public interface OperationDAO {
    Long addOperation(Operation operation);

    void logOperation(Entity entity, Type type, Long id);

    void logOperation(Connection connection, Entity entity, Type type, Long id);
}
