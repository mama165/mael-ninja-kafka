package fr.takima.cdb.dao;

import fr.takima.cdb.model.Company;
import fr.takima.cdb.model.PageRequest;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface CompanyDAO {

    void deleteCompany(Long id);

    /**
     *
     * @param company
     * @return id of the new company in the database aka primary key
     */
    Long addCompany(Company company);

    List<Company> getCompanies();

    Company getCompanyById(Long id);

    void updateCompany(Company company);

    List<Company> getCompaniesWithCustomRequest(PageRequest request);

    int count();
}
