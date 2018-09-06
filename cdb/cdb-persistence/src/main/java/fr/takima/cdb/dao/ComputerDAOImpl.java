package fr.takima.cdb.dao;

import fr.takima.cdb.mapper.CompanyRowMapper;
import fr.takima.cdb.mapper.ComputerRowMapper;
import fr.takima.cdb.model.bean.Computer;
import fr.takima.cdb.model.bean.PageRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ComputerDAOImpl implements ComputerDAO {

    private static final Logger LOG = LoggerFactory.getLogger(ComputerDAOImpl.class);
    private static final String SQL_DELETE_COMPUTER = "DELETE FROM computer WHERE id = ?";
    private static final String SQL_INSERT_COMPUTER = "INSERT INTO computer(name, introduced, discontinued, company_id) VALUES (?, ?, ?, ?)";
    private static final String SQL_UPDATE_COMPUTER = "UPDATE computer SET name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE ID = ?";
    private static final String SQL_FIND_BY_ID_COMPUTER = "SELECT * FROM computer WHERE ID = ?";
    private static final String SQL_FIND_COMPUTERS = "SELECT * FROM computer LEFT JOIN company ON computer.company_id = company.id";
    private static final String SQL_COUNT_COMPUTER = "SELECT COUNT(*) AS total FROM computer";
    private static final String SQL_FIND_BY_REQUEST_COMPUTER = "SELECT * FROM computer LEFT JOIN company ON computer.company_id = company.id LIMIT ?, ?";

    private static final String COMPANY_ID_SQL = "company_id";
    private static final String COMPANY_ID = "company.id";

    private static final String LEFT = " LEFT JOIN company ON " + COMPANY_ID_SQL + " = " + COMPANY_ID;
    private static final String WHERE_ID = " WHERE computer.id=?";
    private static final String ORDER_BY_COMPUTER_ASC = " ORDER BY computer.name ASC ";
    private static final String ORDER_BY_COMPUTER_DESC = " ORDER BY computer.name DESC ";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void deleteComputer(Long id) {
        String sql = SQL_DELETE_COMPUTER;
        this.jdbcTemplate.update(sql, id);
    }

    @Override
    public Long addComputer(Computer computer) {
        String sql = SQL_INSERT_COMPUTER;
        KeyHolder keyHolder = new GeneratedKeyHolder();
        this.jdbcTemplate.update(sql,
                computer.getName(),
                computer.getIntroducedDate(),
                computer.getDiscontinuedDate(),
                computer.getManufacturer().getId(),
                keyHolder);
        return keyHolder.getKey().longValue();
    }

    @Override
    public List<Computer> getComputers() {
        String sql = SQL_FIND_COMPUTERS;
        RowMapper<Computer> rowMapper = new ComputerRowMapper();
        return this.jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public Computer getComputerById(Long id) {
        String sql = SQL_FIND_BY_ID_COMPUTER;
        RowMapper<Computer> rowMapper = new ComputerRowMapper();
        return this.jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    @Override
    public void updateComputer(Computer computer) {
        String sql = SQL_UPDATE_COMPUTER;
        this.jdbcTemplate.update(sql, computer.getName(),
                computer.getIntroducedDate(),
                computer.getDiscontinuedDate(),
                computer.getManufacturer().getId(),
                computer.getId());
    }

    @Override
    public List<Computer> getComputersWithCustomRequest(PageRequest request) {
        String sql = SQL_FIND_BY_REQUEST_COMPUTER;
        RowMapper<Computer> rowMapper = new ComputerRowMapper();
        return this.jdbcTemplate.query(sql, rowMapper, request.getOffset(), request.getPageSize());
    }

    @Override
    public int count() {
        String sql = SQL_COUNT_COMPUTER;
        return this.jdbcTemplate.queryForObject(sql, Integer.class);
    }
}
