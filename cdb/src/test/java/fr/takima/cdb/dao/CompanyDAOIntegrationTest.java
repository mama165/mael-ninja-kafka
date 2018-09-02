package fr.takima.cdb.dao;

import fr.takima.cdb.model.Company;
import fr.takima.cdb.model.PageRequest;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;

import static org.junit.Assert.*;

@ComponentScan

public class CompanyDAOIntegrationTest {

    static final Logger LOG = LoggerFactory.getLogger(CompanyDAOIntegrationTest.class);

    @Autowired
    private CompanyDAO companyDAO;
    private Long id;

    @Test
    public void addSomeCompany(){
        Company company = new Company("takima");
        id = companyDAO.addCompany(company);
        company.setId(id);
        Company c = companyDAO.getCompanyById(id);
        assertEquals("Company are not the same", company, c);
    }

    @Test
    public void deleteSomeCompany(){
        Company company = new Company("takima");
        id = companyDAO.addCompany(company);

        companyDAO.deleteCompany(id);

        try {
            companyDAO.getCompanyById(id);
        } catch (RuntimeException e){
            assertEquals("No company found " + id, e.getMessage());
        }
    }

    @Test
    public void getCompanies() {
        List<Company> companies;
        companies = companyDAO.getCompanies();
        assertFalse(companies.isEmpty());
    }

    @Test
    public void updateCompany() {
        Company company = new Company("takima");
        id = companyDAO.addCompany(company);
        company.setId(id);
        company.setName("takimouche");
        companyDAO.updateCompany(company);
        assertEquals("takimouche", company.getName());
    }

    @Test
    public void getCompaniesWithCustomRequest() {
        List<Company> companies;
        PageRequest pageRequest = new PageRequest.Builder().build();
        pageRequest.setOffset(8);
        companies = companyDAO.getCompaniesWithCustomRequest(pageRequest);
        assertFalse(companies.isEmpty());
    }

    @Test
    public void getTotalCompanies() {
        int totalElements = companyDAO.getCompanies().size();
        LOG.debug("totalElements " + totalElements);
        int count = companyDAO.count();
        assertEquals(count, totalElements);
    }

}
