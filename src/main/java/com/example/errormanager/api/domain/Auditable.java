package com.example.errormanager.api.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author Muhammadkomil Murodillayev, ср 11:06. 8/10/22
 */

@Getter
@Setter
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
public class Auditable implements BaseDomain{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(columnDefinition = "boolean default false")
    protected boolean deleted;

    @Column(columnDefinition = "int default 1")
    protected short status;

    @Column(columnDefinition = "timestamp default now()")
    protected LocalDateTime createdAt;

    @Column(columnDefinition = "bigint default -1")
    protected Long createdBy;

    protected LocalDateTime updatedAt;

    protected Long updatedBy;

}
