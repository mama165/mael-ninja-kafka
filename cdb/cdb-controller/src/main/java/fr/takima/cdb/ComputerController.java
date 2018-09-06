package fr.takima.cdb;

import fr.takima.cdb.model.bean.*;
import fr.takima.cdb.model.dto.PageResponse;
import fr.takima.cdb.service.ComputerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.Instant;

@RestController
@RequestMapping(value = "/computers")
public class ComputerController {
    @Autowired
    private ComputerService computerService;
    static final Logger LOG = LoggerFactory.getLogger(ComputerController.class);

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getComputers(@RequestParam(value = "pageIndex", defaultValue = "1") int pageIndex,
                                       @RequestParam(value = "pageSize", defaultValue = "20") int pageSize,
                                       //@RequestParam(value = "sortColumn", defaultValue = "") String sortColumn,
                                       //@RequestParam(value = "ASC", defaultValue = "sortOrientation") Orientation sortOrientation,
                                       @RequestParam(value = "search", defaultValue = "") String search) {

        Instant before = Instant.now();


        // TODO: search
        // TODO: ajouter les fcts post put delete dans le computer
        // TODO: ajouter  post put delete company
        //TODO:  search , sort, computer
        // TODO: search
        // TODO: faire une ResponseEntity avec des DTOs


        Search searchObject = new Search(search);
        Sort sort = new Sort("", Orientation.ASCENDING);

        PageRequest pageRequest = new PageRequest.Builder()
                .offset(pageIndex)
                .pageSize(pageSize)
                .search(searchObject)
                .sort(sort)
                .build();

        Page page = computerService.open(pageRequest);

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

        Instant after = Instant.now();
        Duration time = Duration.between(before, after);

        LOG.info("Page created in : " + time.toMillis() + " ms");
        return new ResponseEntity(pageResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity getComputerById(@PathVariable(value = "id") Long id) {

        Computer computer = computerService.getComputerById(id);
        LOG.warn("Computer ", computer);

        if (computer != null) {
            return new ResponseEntity(computer, HttpStatus.OK);
        } else {
            LOG.warn("Computer not found with the id: ", id);
            return new ResponseEntity(computer, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteComputerById(@PathVariable(value = "id") Long id) {

        LOG.info("Fetching and deleting computer : " + id);

        //TODO check if computer exist or not ??!
        computerService.deleteComputer(id);
        LOG.warn("Computer deleted with id:  ", id);

        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity createComputer(@RequestBody Computer computer) {

        LOG.info("Creating computer");
        Long id;
        if (computer != null && computer.getId() == null) {
            id = computerService.addComputer(computer);
            if (id == null) {
                LOG.warn("Company could not be created");
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
        } else {
            computerService.updateComputer(computer);
            id = computer.getId();
        }

        LOG.info("Company with id : " + id + " created/updated");
        return new ResponseEntity(computer, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity updateComputerById(@PathVariable(value = "id") Long id,
                                             @RequestBody Computer computer) {
        LOG.info("Updating computer : " + id);

        Computer c = computerService.getComputerById(id);

        if (c == null) {
            LOG.warn("Computer with id : " + id + "not found");
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        Computer comp = new Computer.Builder(c.getId(),
                computer.getName())
                .introducedDate(computer.getIntroducedDate())
                .discontinuedDate(computer.getDiscontinuedDate())
                .manufacturer(computer.getManufacturer())
                .build();
        computerService.updateComputer(comp);
        LOG.info("Computer updated : " + id);
        return new ResponseEntity(computer, HttpStatus.OK);
    }
}
