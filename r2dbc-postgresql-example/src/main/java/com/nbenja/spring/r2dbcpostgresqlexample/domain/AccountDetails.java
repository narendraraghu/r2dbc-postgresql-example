package com.nbenja.spring.r2dbcpostgresqlexample.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("account")
@Data
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class AccountDetails {

    @Id
    private String account_number;
    private String account_name;
    private String address;
    @Column("phone_number")
    private Integer phone;
}
