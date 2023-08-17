package com.example.SpringBatch.reader;

import com.example.SpringBatch.Entity.StudentJdbc;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class JDBCItemReader extends JdbcCursorItemReader {


    @Autowired
    DataSource datasource;

    public JdbcCursorItemReader getJdbcItemReader() {
        JdbcCursorItemReader<StudentJdbc> jdbcCursorItemReader =
                new JdbcCursorItemReader<StudentJdbc>();

        jdbcCursorItemReader.setDataSource(datasource);
        jdbcCursorItemReader.setSql(
                "select id, first_name as firstName, last_name as lastName,"
                        + "email from springbatchjobconfigs.student");

        jdbcCursorItemReader.setRowMapper(new BeanPropertyRowMapper<StudentJdbc>() {
            {
                setMappedClass(StudentJdbc.class);
            }
        });

        jdbcCursorItemReader.setCurrentItemCount(2);
        jdbcCursorItemReader.setMaxItemCount(8);

        return jdbcCursorItemReader;
    }
}
