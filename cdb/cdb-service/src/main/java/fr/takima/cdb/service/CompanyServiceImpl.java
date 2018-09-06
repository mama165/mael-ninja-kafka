package fr.takima.cdb.service;

import fr.takima.cdb.model.bean.Company;
import fr.takima.cdb.model.bean.Page;
import fr.takima.cdb.model.bean.PageRequest;
import fr.takima.cdb.dao.CompanyDAO;
import fr.takima.cdb.dao.OperationDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl  implements CompanyService {

    private CompanyDAO companyDAO;
    private OperationDAO operationDAO;

    @Autowired
    public CompanyServiceImpl(CompanyDAO companyDAO, OperationDAO operationDAO) {
        this.companyDAO = companyDAO;
        this.operationDAO = operationDAO;
    }

    @Override
    public void deleteCompany(Long id) {
        this.companyDAO.deleteCompany(id);
    }

    @Override
    public Long addCompany(Company company) {
        return this.companyDAO.addCompany(company);
    }

    @Override
    public List<Company> getCompanies() {
        return this.companyDAO.getCompanies();
    }

    @Override
    public Company getCompanyById(Long id) {
        return this.companyDAO.getCompanyById(id);
    }

    @Override
    public void updateCompany(Company company) {
        this.companyDAO.updateCompany(company);
    }

    @Override
    public List list(PageRequest pageRequest) {
        return this.companyDAO.getCompaniesWithCustomRequest(pageRequest);
    }

    @Override
    public Page open(PageRequest pageRequest) {
        // TODO: override count(Search)
        //int itemsCount = this.count(pageRequest.getSearch());
        int totalElements = this.count();

        List<Company> items = this.list(pageRequest);

        int totalPages = totalElements / pageRequest.getPageSize();

        if(totalElements % pageRequest.getPageSize() != 0) {
            totalPages++;
        }

        return new Page(pageRequest.getPageIndex(), items, totalElements, totalPages);
    }

    @Override
    public int count() {
        return this.companyDAO.count();
    }
}
