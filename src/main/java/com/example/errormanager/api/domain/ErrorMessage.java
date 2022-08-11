package com.example.errormanager.api.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.InputStream;
import java.time.LocalDateTime;

/**
 * @author Muhammadkomil Murodillayev, ср 10:59. 8/10/22
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessage implements BaseDomain{

    private InputStream stream;

    private String errorText;

    private Long projectId;

    private LocalDateTime happenTime;

}
