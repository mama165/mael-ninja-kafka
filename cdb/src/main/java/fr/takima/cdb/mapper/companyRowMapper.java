package fr.takima.cdb.mapper;

import fr.takima.cdb.model.Company;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class companyRowMapper implements RowMapper<Company> {
    @Override
    public Company mapRow(ResultSet row, int rowNum) throws SQLException {

        Company company = new Company(row.getLong("id"), row.getString("name"));
        /*company.setId(1L);
        company.setName(row.getString("name"));*/
        return company;
    }
}
