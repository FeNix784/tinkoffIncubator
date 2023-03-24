package ru.tkachev.tinkoffIncubator.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.tkachev.tinkoffIncubator.entity.TranslationEntity;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
public class TranslationRepository {

    private final JdbcTemplate jdbcTemplate;

    @Inject
    public TranslationRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void save(TranslationEntity translation) {

        String sql = "INSERT INTO translation_entity (TRANSLATED_WORD, WORD, REQUEST_ID) VALUES (?, ?, ?)";

        jdbcTemplate.update(connection -> {

            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, translation.getTranslatedWord());
            preparedStatement.setString(2, translation.getWord());
            preparedStatement.setLong(3, translation.getRequest().getId());
            return preparedStatement;

        });
    }
}
