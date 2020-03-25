package com.ule.demo.common.entity;

import com.ule.demo.common.springside.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "TEST")
@Getter
@Setter
public class Test extends AbstractEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select testsequence.nextval from dual")
    private Long id;

    private String userName;

}