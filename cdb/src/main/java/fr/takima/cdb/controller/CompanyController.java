package fr.takima.cdb.controller;

import fr.takima.cdb.dto.PageResponse;
import fr.takima.cdb.model.*;
import fr.takima.cdb.service.CompanyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sun.rmi.runtime.Log;

@RestController
@RequestMapping(value = "/companies")
public class CompanyController {

    @Autowired
    private CompanyService companyService;
    static final Logger LOG = LoggerFactory.getLogger(CompanyController.class);

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getCompanies(@RequestParam(value = "pageIndex", defaultValue = "1") int pageIndex,
                                       @RequestParam(value = "pageSize", defaultValue = "20") int pageSize,
                                       @RequestParam(value = "sortColumn", defaultValue = "") String sortColumn,
                                       @RequestParam(value = "ASC", defaultValue = "sortOrientation") String sortOrientation,
                                       @RequestParam(value = "search", defaultValue = "") String search) {

        // TODO: search
        Search searchObject = new Search(search);
        Sort sort = new Sort("", Orientation.ASCENDING);

        PageRequest pageRequest = new PageRequest.Builder()
                .offset(pageIndex)
                .pageSize(pageSize)
                .search(searchObject)
                .sort(sort)
                .build();

        Page page = companyService.open(pageRequest);

        if (page == null) {
            LOG.warn("Company not found");
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        PageResponse pageResponse =
                new PageResponse.Builder()
                        .pageIndex(pageIndex)
                        .pageSize(pageSize)
                        .search(searchObject)
                        .sort(sort)
                        .totalElements(page.getTotalElements())
                        .totalPages(page.getTotalPages())
                        .elements(page.getElements())
                        .build();

        return new ResponseEntity(pageResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity getCompanyById(@PathVariable(value = "id") Long id) {

        LOG.info("Fetching company with id : ", id);
        Company company = companyService.getCompanyById(id);

        if (company == null) {
            LOG.warn("Company not found with the id: ", id);
            return new ResponseEntity(company, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(company, HttpStatus.OK);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteCompanyById(@PathVariable(value = "id") Long id) {

        LOG.info("Fetching and Deleting company : " + id);

        Company company = companyService.getCompanyById(id);
        if (company == null) {
            LOG.warn("Company not found with the id: ", id);
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        companyService.deleteCompany(id);
        LOG.info("Company deleted with id:  ", id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity updateCompanyById(@PathVariable(value = "id") Long id,
                                            @RequestBody Company company) {
        LOG.info("Updating company : " + id);

        Company c = companyService.getCompanyById(id);

        if (c == null) {
            LOG.warn("Company with id : " + id + "not found");
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        c.setName(company.getName());
        companyService.updateCompany(c);
        LOG.info("Company updated : " + id);
        return new ResponseEntity(company, HttpStatus.OK);
    }
}
