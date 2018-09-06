package fr.takima.cdb.dao;

import fr.takima.cdb.model.bean.Computer;
import fr.takima.cdb.model.bean.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComputerDAO {

    void deleteComputer(Long id);

    Long addComputer(Computer computer);

    List<Computer> getComputers();

    Computer getComputerById(Long id);

    void updateComputer(Computer computer);

    List<Computer> getComputersWithCustomRequest(PageRequest request);

    int count();
}
