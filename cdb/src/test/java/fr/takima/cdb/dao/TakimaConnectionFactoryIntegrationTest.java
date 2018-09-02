package fr.takima.cdb.dao;

import fr.takima.cdb.dao.ComputerDAO;
import fr.takima.cdb.dao.ComputerDAOImpl;
import fr.takima.cdb.model.Computer;
import fr.takima.cdb.model.PageRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class TakimaConnectionFactoryIntegrationTest {

    private ComputerDAO computerDAO;
    private Long id;

    static final Logger LOG = LoggerFactory.getLogger(TakimaConnectionFactoryIntegrationTest.class);


    @Test
    @DisplayName("shouldGetAListOfComputers")
    public void getComputers() {
        List<Computer> computers;
        computers = computerDAO.getComputers();
        LOG.debug("computers " + computers);
        assertFalse(computers.isEmpty());
    }

    @Test
    @DisplayName("shouldGetAComputerById")
    public void getComputer() {
        id = 1L;
        Computer computerFound = computerDAO.getComputerById(id);
        LOG.debug("computerFound " + computerFound);
        assertEquals("MacBook Pro 15.4 inch", computerFound.getName());
    }

    @Test
    @DisplayName("shouldAddAComputerById")
    public void addComputer() {
        Computer computerToAdd = new Computer.Builder("TakimaComputer").build();
        id = computerDAO.addComputer(computerToAdd);
        computerToAdd.setId(id);
        Computer c = computerDAO.getComputerById(id);
        //assertEquals("Computers are not the same", computerToAdd, c);
    }

    @Test
    @DisplayName("shouldDeleteComputerById")
    public void deleteComputer() {
        Computer computerToDelete = new Computer.Builder("TakimaComputer").build();
        id = computerDAO.addComputer(computerToDelete);

        computerDAO.deleteComputer(id);

        try {
            computerDAO.getComputerById(id);
        } catch (RuntimeException e){
            assertEquals("No computer found " + id, e.getMessage());
        }
    }

    @Test
    @DisplayName("shouldUpdateComputer")
    public void updateComputer() {
        Computer computerToUpdate = new Computer.Builder("TakimaComputer").build();
        id = computerDAO.addComputer(computerToUpdate);
        computerToUpdate.setId(id);
        computerToUpdate.setName("TakimoucheComputer");
        computerDAO.updateComputer(computerToUpdate);
        assertEquals("TakimoucheComputer", computerToUpdate.getName());
    }

    @Test
    public void getComputersWithCustomRequest() {
        List<Computer> computers;
        PageRequest pageRequest = new PageRequest.Builder().build();
        pageRequest.setOffset(0);
        computers = computerDAO.getComputersWithCustomRequest(pageRequest);
        assertFalse(computers.isEmpty());
    }

    @Test
    public void getTotalComputers() {
        int totalElements = computerDAO.getComputers().size();
        LOG.debug("totalElements " + totalElements);
        int count = computerDAO.count();
        assertEquals(count, totalElements);
    }
}
