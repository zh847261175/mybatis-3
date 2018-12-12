package com.haode.mybatis.builder.entity;

import lombok.Data;
import lombok.ToString;

/**
 * @author zhuh
 * @date 2018/12/12
 **/
@Data
@ToString
public class City {
	private String id;
	private String name;
	private Country country;
	private String district;
	private String population;
}
