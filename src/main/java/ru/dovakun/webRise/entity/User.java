package ru.dovakun.webRise.entity;

import io.r2dbc.spi.Row;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.function.Function;

@Data
@Table("users")
public class User {
    @Id
    private Long id;
    private String name;
    private String email;

    public static final Function<Row, User> MAPPER = row -> {
        User user = new User();
        user.setId(row.get("id", Long.class));
        user.setName(row.get("name", String.class));
        user.setEmail(row.get("email", String.class));
        return user;
    };
}