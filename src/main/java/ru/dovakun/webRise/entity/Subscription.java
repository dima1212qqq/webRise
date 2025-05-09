package ru.dovakun.webRise.entity;

import io.r2dbc.spi.Row;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.function.Function;

@Data
@Table("subscriptions")
public class Subscription {
    @Id
    private Long id;
    private String serviceName;
    private Long userId;

    public static final Function<Row, Subscription> MAPPER = row -> {
        Subscription subscription = new Subscription();
        subscription.setId(row.get("id", Long.class));
        subscription.setServiceName(row.get("service_name", String.class));
        subscription.setUserId(row.get("user_id", Long.class));
        return subscription;
    };
}