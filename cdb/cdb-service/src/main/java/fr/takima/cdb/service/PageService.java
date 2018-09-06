package fr.takima.cdb.service;

import fr.takima.cdb.model.bean.Page;
import fr.takima.cdb.model.bean.PageRequest;

import java.util.List;

public interface PageService<T> {

    List<T> list(PageRequest pageRequest);
    Page<T> open(PageRequest pageRequest);
    int count();
}
