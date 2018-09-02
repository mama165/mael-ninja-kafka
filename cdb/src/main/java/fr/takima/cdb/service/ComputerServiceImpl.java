package fr.takima.cdb.service;

import fr.takima.cdb.dao.ComputerDAO;
import fr.takima.cdb.dao.OperationDAO;
import fr.takima.cdb.dao.exception.DAOException;
import fr.takima.cdb.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComputerServiceImpl implements ComputerService {

    private ComputerDAO computerDAO;
    private OperationDAO operationDAO;

    @Autowired
    public ComputerServiceImpl(ComputerDAO computerDAO, OperationDAO operationDAO) {
        this.computerDAO = computerDAO;
        this.operationDAO = operationDAO;
    }


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
    public List<Computer> getComputersWithCustomRequest(PageRequest pageRequest) {
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
    public List list(PageRequest pageRequest) {
        return null;
    }

    @Override
    public Page open(PageRequest pageRequest) {
        return null;
    }

    @Override
    public int count() {
        return 0;
    }
}
