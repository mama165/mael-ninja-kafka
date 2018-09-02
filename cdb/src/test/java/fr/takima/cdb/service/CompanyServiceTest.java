package fr.takima.cdb.service;

import fr.takima.cdb.dao.CompanyDAO;
import fr.takima.cdb.model.Company;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;

public class CompanyServiceTest {

    @Mock
    private CompanyDAO companyDAO;

    @InjectMocks
    private CompanyServiceImpl companyService;

    private List<Company> companies;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);

        companies = new ArrayList<>();
        companies.add(new Company(1L, "Apple Inc."));
        companies.add(new Company(2L, "Thinking Machines"));
        companies.add(new Company(3L, "RCA"));
    }

    @Test
    @DisplayName("shouldCallDeleteCompanyFromDAO")
    public void shouldCallDeleteCompanyFromDAOTest() {
        final Long companyToDeleteId = 1L;
        companyService.deleteCompany(companyToDeleteId);
        verify(companyDAO).deleteCompany(companyToDeleteId);
        //assertEquals(2, companies.size());
    }

    @Test
    @DisplayName("shouldCallAddCompanyFromDAO")
    public void shouldCallAddCompanyFromDAOTest() {
        final Company company = new Company("takima");
        companyService.addCompany(company);
        verify(companyDAO).addCompany(company);
    }

    @Test
    @DisplayName("shouldCallGetCompaniesFromDAO")
    public void shouldCallGetCompaniesFromDAOTest() {
        companyService.getCompanies();
        verify(companyDAO).getCompanies();
    }

    @Test
    @DisplayName("shouldCallGetCompanyByIdFromDAO")
    public void shouldCallGetCompanyByIdFromDAOTest() {
        final Long companyToFindId = 1L;
        companyService.getCompanyById(companyToFindId);
        verify(companyDAO).getCompanyById(companyToFindId);
    }

    @Test
    @DisplayName("shouldCallUpdateCompanyFromDAO")
    public void shouldCallUpdateCompanyFromDAOTest() {
        final Company companyToUpdate = new Company(1L, "takima");
        companyService.updateCompany(companyToUpdate);
        verify(companyDAO).updateCompany(companyToUpdate);
    }

}
