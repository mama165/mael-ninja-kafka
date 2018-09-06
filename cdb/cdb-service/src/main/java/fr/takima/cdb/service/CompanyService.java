package fr.takima.cdb.service;

import fr.takima.cdb.model.bean.Company;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CompanyService extends PageService {

    void deleteCompany(Long id);

    Long addCompany(Company company);

    List<Company> getCompanies();

    Company getCompanyById(Long id);

    void updateCompany(Company company);

}