package fr.takima.cdb.service;

import fr.takima.cdb.dao.ComputerDAO;
import fr.takima.cdb.dao.OperationDAO;

import fr.takima.cdb.model.bean.Computer;
import fr.takima.cdb.model.bean.Entity;
import fr.takima.cdb.model.bean.PageRequest;
import fr.takima.cdb.model.bean.Type;
import fr.takima.cdb.model.bean.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public void deleteComputer(Long id) {
        this.operationDAO.logOperation(Entity.COMPUTER, Type.DELETION, id);
        this.computerDAO.deleteComputer(id);
    }

    @Override
    @Transactional
    public Long addComputer(Computer computer) {
        operationDAO.logOperation(Entity.COMPUTER, Type.CREATION, computer.getId());
        return this.computerDAO.addComputer(computer);
    }

    @Override
    public List<Computer> getComputers() {
        return this.computerDAO.getComputers();
    }

    @Override
    public List<Computer> getComputersWithCustomRequest(PageRequest pageRequest) {
        return this.computerDAO.getComputersWithCustomRequest(pageRequest );
    }

    @Override
    public Computer getComputerById(Long id) {
        return this.computerDAO.getComputerById(id);
    }

    @Override
    @Transactional
    public void updateComputer(Computer computer) {
        this.operationDAO.logOperation(Entity.COMPUTER, Type.UPDATE, computer.getId());
        this.computerDAO.updateComputer(computer);
    }

    @Override
    public List list(PageRequest pageRequest) {
        return this.computerDAO.getComputersWithCustomRequest(pageRequest);
    }

    @Override
    public Page open(PageRequest pageRequest) {
        //int itemsCount = this.count(pageRequest.getSearch());
        int totalElements = this.count();

        List<Computer> items = this.list(pageRequest);

        int totalPages = totalElements / pageRequest.getPageSize();

        if (totalElements % pageRequest.getPageSize() != 0) {
            totalPages++;
        }
        return new Page(pageRequest.getPageIndex(), items, totalElements, totalPages);

    }

    @Override
    public int count() {
        return 0;
    }
}
