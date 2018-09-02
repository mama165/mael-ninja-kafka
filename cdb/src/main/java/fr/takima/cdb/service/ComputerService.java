package fr.takima.cdb.service;

import fr.takima.cdb.model.Computer;
import fr.takima.cdb.model.PageRequest;

import java.util.List;

public interface ComputerService extends PageService {

    void deleteComputer(Long id);

    Long addComputer(Computer computer);

    List<Computer> getComputers();

    List<Computer> getComputersWithCustomRequest(PageRequest pageRequest);

    Computer getComputerById(Long id);

    void updateComputer(Computer computer);
}
