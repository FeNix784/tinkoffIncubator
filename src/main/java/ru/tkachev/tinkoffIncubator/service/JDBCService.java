package ru.tkachev.tinkoffIncubator.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import ru.tkachev.tinkoffIncubator.entity.Request;
import ru.tkachev.tinkoffIncubator.entity.Translation;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Objects;

@Service
public class JDBCService {

    private final JdbcTemplate jdbcTemplate;

    @Inject
    public JDBCService(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Long insertRequest(Request request) {

        String sql = "INSERT INTO request (INPUT_DATA, OUTPUT_DATA, SOURCE_LANGUAGE, TARGET_LANGUAGE, IPADDRESS, REQUEST_TIME) VALUES (?, ?, ?, ?, ?, ?)";

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

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    public void updateRequest(Request request) {

        String sql = "UPDATE request SET OUTPUT_DATA = ? WHERE ID = ?";

        jdbcTemplate.update(connection -> {

            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, request.getOutputData());
            preparedStatement.setLong(2, request.getId());
            return preparedStatement;

        });

    }

    public void insertTranslation(Translation translation) {

        String sql = "INSERT INTO translation (TRANSLATED_WORD, WORD, REQUEST_ID) VALUES (?, ?, ?)";

        jdbcTemplate.update(connection -> {

                    PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                    preparedStatement.setString(1, translation.getTranslatedWord());
                    preparedStatement.setString(2, translation.getWord());
                    preparedStatement.setLong(3, translation.getRequest().getId());
            return preparedStatement;

        });

    }
}
