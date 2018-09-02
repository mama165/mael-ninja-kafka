package fr.takima.cdb.model;

import java.util.Objects;

public class Operation {
    private Long id;
    private String entity;
    private String type;
    private Long entity_id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getEntityId() {
        return entity_id;
    }

    public void setEntity_id(Long entity_id) {
        this.entity_id = entity_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Operation operation = (Operation) o;
        return Objects.equals(id, operation.id) &&
                Objects.equals(entity, operation.entity) &&
                Objects.equals(type, operation.type) &&
                Objects.equals(entity_id, operation.entity_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, entity, type, entity_id);
    }
}
