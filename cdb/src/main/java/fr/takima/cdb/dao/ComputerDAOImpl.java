package fr.takima.cdb.dao;

import fr.takima.cdb.dao.exception.DAOException;
import fr.takima.cdb.model.Company;
import fr.takima.cdb.model.Computer;
import fr.takima.cdb.model.PageRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Repository
public class ComputerDAOImpl implements ComputerDAO {

    private static final Logger LOG = LoggerFactory.getLogger(ComputerDAOImpl.class);

    @Override
    public void deleteComputer(Long id) {

    }

    @Override
    public Long addComputer(Computer computer) {
        return null;
    }

    @Override
    public List<Computer> getComputers() {
        return null;
    }

    @Override
    public Computer getComputerById(Long id) {
        return null;
    }

    @Override
    public void updateComputer(Computer computer) {

    }

    @Override
    public List<Computer> getComputersWithCustomRequest(PageRequest request) {
        return null;
    }

    @Override
    public int count() {
        return 0;
    }
}
