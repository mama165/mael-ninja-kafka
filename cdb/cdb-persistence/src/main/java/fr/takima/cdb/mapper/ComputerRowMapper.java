package fr.takima.cdb.mapper;

import fr.takima.cdb.model.bean.Company;
import fr.takima.cdb.model.bean.Computer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class ComputerRowMapper implements RowMapper<Computer> {
    private static final String COLUMN_COMPUTER_ID = "computer.id";
    private static final String COLUMN_COMPUTER_NAME = "computer.name";
    private static final String COLUMN_COMPANY_ID = "company.id";
    private static final String COLUMN_COMPANY_NAME = "company.name";
    private static final String COLUMN_INTRODUCED = "introduced";
    private static final String COLUMN_DISCONTINUED = "discontinued";

    @Override
    public Computer mapRow(ResultSet row, int i) throws SQLException {
        Long computerID = row.getLong(COLUMN_COMPUTER_ID);
        String computerName = row.getString(COLUMN_COMPUTER_NAME);

        LocalDate introducedDate = (row.getDate(COLUMN_INTRODUCED) == null) ? null : row.getDate(COLUMN_INTRODUCED).toLocalDate();
        LocalDate discontinuedDate = (row.getDate(COLUMN_DISCONTINUED) == null) ? null : row.getDate(COLUMN_DISCONTINUED).toLocalDate();

        Long companyId = (row.getObject(COLUMN_COMPANY_ID) == null) ? null : row.getLong(COLUMN_COMPANY_ID);
        String companyName = row.getString(COLUMN_COMPANY_NAME);
        Company company = new Company(companyId, companyName);

        Computer computer = new Computer.Builder(computerID, computerName)
                .introducedDate(introducedDate)
                .discontinuedDate(discontinuedDate)
                .manufacturer(company)
                .build();
        return computer;
    }
}
