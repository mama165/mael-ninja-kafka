package fr.takima.cdb.service;

import fr.takima.cdb.dao.ComputerDAO;
import fr.takima.cdb.model.Company;
import fr.takima.cdb.model.Computer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;

public class ComputerServiceTest {

    @Mock
    private ComputerDAO computerDAO;

    @InjectMocks
    private ComputerServiceImpl computerService;

    private List<Computer> computers;

    private List<Company> companies;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);

        //computerService = new ComputerServiceImpl(computerDAO);

        companies = new ArrayList<>();
        companies.add(new Company(1L, "Apple Inc."));
        companies.add(new Company(2L, "Thinking Machines"));
        companies.add(new Company(3L, "RCA"));

        computers = new ArrayList<>();
        //computers.add(new Computer.Builder(1L, "MacBook Pro 15.4 inch", companies.get(0)).build());
        //computers.add(new Computer.Builder(2L, "CM-2a", companies.get(1)).build());
        //computers.add(new Computer.Builder(3L, "CM-200", companies.get(2)).build());
    }

    @DisplayName("shouldCallDeleteComputerFromDAO")
    @Test
    public void shouldCallDeleteComputerFromDAOTest() {
        final Long computerToDeleteId = 1L;
        computerService.deleteComputer(computerToDeleteId);
        verify(computerDAO).deleteComputer(computerToDeleteId);
        //assertEquals(2, computers.size());
    }

    @DisplayName("shouldCallAddComputerFromDAO")
    @Test
    public void shouldCallAddComputerFromDAOTest() {
        final Computer computerToAdd = new Computer.Builder(1L, "MacBook Pro 15.4 inch").build();
        computerService.addComputer(computerToAdd);
        verify(computerDAO).addComputer(computerToAdd);
    }

    @DisplayName("shouldCallGetComputersFromDAO")
    @Test
    public void shouldCallGetComputersFromDAOTest() {
        computerService.getComputers();
        verify(computerDAO).getComputers();
    }

    @DisplayName("shouldCallGetComputerByIdFromDAO")
    @Test
    public void shouldCallGetComputerByIdFromDAOTest() {
        final Long computerToFindId = 1L;
        computerService.getComputerById(computerToFindId);
        verify(computerDAO).getComputerById(computerToFindId);
    }

    @DisplayName("shouldCallUpdateComputer")
    @Test
    public void shouldCallUpdateComputerFromDAOTest() {
        final Computer computerToUpdate = new Computer.Builder(1L, "MacBook Pro 15.4 inch").build();
        computerService.updateComputer(computerToUpdate);
        verify(computerDAO).updateComputer(computerToUpdate);
    }

}
