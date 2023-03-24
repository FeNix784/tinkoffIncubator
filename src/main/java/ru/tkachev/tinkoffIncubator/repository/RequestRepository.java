package ru.tkachev.tinkoffIncubator.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.tkachev.tinkoffIncubator.entity.RequestEntity;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Objects;

@Repository
public class RequestRepository {

    private final JdbcTemplate jdbcTemplate;

    @Inject
    public RequestRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void save(RequestEntity request) {

        String sql = "INSERT INTO request_entity (INPUT_DATA, OUTPUT_DATA, SOURCE_LANGUAGE, TARGET_LANGUAGE, IPADDRESS, REQUEST_TIME) VALUES (?, ?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {

            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, request.getInputData());
            preparedStatement.setString(2, request.getOutputData());
            preparedStatement.setString(3, request.getSourceLanguage());
            preparedStatement.setString(4, request.getTargetLanguage());
            preparedStatement.setString(5, request.getIPAddress());
            preparedStatement.setTimestamp(6, request.getRequestTime());
            return preparedStatement;

        }, keyHolder);

        request.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());
    }
}
