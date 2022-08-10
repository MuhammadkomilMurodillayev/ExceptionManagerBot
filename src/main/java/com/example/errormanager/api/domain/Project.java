package com.example.errormanager.api.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Set;

/**
 * @author Muhammadkomil Murodillayev, ср 10:59. 8/10/22
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Project extends Auditable{

    private String name;


}
