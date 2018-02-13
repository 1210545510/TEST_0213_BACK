package com.aml.common.util;

import org.apache.tomcat.jdbc.pool.DataSource;

public class DataSourceRewrite extends DataSource{

    @Override
    public void setPassword(String password) {
    	password = CodeUtils.Decrypt(password, 6, 9);
        this.poolProperties.setPassword(password);
        this.poolProperties.getDbProperties().setProperty("password",this.poolProperties.getPassword());
    }
}
