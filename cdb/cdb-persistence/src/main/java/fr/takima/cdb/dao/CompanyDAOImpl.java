package fr.takima.cdb.dao;

import com.querydsl.jpa.impl.JPAQueryFactory;
import fr.takima.cdb.mapper.CompanyRowMapper;
import fr.takima.cdb.model.bean.Company;
import fr.takima.cdb.model.bean.PageRequest;
import fr.takima.cdb.model.bean.QCompany;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CompanyDAOImpl implements CompanyDAO {

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String SQL_DELETE_COMPANY = "DELETE FROM company WHERE id = ?";
    private static final String SQL_INSERT_COMPANY = "INSERT INTO company (name) VALUES (?)";
    private static final String SQL_UPDATE_COMPANY = "UPDATE company SET name = ? WHERE id = ?";
    private static final String SQL_FIND_BY_ID_COMPANY = "SELECT * FROM company where id = ?";
    private static final String SQL_FIND_COMPANIES = "SELECT id, name FROM company";
    private static final String SQL_COUNT_COMPANY = "SELECT COUNT(*) AS total FROM company";
    private static final String SQL_FIND_BY_REQUEST_COMPANY = "SELECT * FROM company LIMIT ?, ?";

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void deleteCompany(Long id) {
        String sql = SQL_DELETE_COMPANY;
        this.jdbcTemplate.update(sql, id);
    }

    @Override
    public Long addCompany(Company company) {
        String sql = SQL_INSERT_COMPANY;
        KeyHolder keyHolder = new GeneratedKeyHolder();
        this.jdbcTemplate.update(sql, company.getName(), keyHolder);
        return keyHolder.getKey().longValue();
    }

    @Override
    public List<Company> getCompanies() {
        String sql = SQL_FIND_COMPANIES;
        RowMapper<Company> rowMapper = new CompanyRowMapper();
        return this.jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public Company getCompanyById(Long id) {
        String sql = SQL_FIND_BY_ID_COMPANY;
        RowMapper<Company> rowMapper = new CompanyRowMapper();
        return this.jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    @Override
    public void updateCompany(Company company) {
        String sql = SQL_UPDATE_COMPANY;
        this.jdbcTemplate.update(sql, company.getName(), company.getId());
    }

    @Override
    public List<Company> getCompaniesWithCustomRequest(PageRequest request) {


        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);


        QCompany qcompany = QCompany.company;

        /*Company c = queryFactory.selectFrom(qcompany)
                .where(qcompany.name.eq("ASUS"))
                .fetchOne();*/

        List<Company> companies =
                queryFactory.selectFrom(qcompany)
                        .offset(request.getOffset())
                        .limit(request.getPageSize()).fetch();

        return companies;

        //"SELECT * FROM company LIMIT ?, ?";
        /*String sql = SQL_FIND_BY_REQUEST_COMPANY;
        RowMapper<Company> rowMapper = new CompanyRowMapper();
        return this.jdbcTemplate.query(sql, rowMapper, request.getOffset(), request.getPageSize());*/

    }

    @Override
    public int count() {
        String sql = SQL_COUNT_COMPANY;
        return this.jdbcTemplate.queryForObject(sql, Integer.class);
    }
}
