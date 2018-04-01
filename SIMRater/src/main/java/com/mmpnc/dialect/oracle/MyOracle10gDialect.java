package com.mmpnc.dialect.oracle;

import java.sql.Types;

import org.hibernate.dialect.Oracle10gDialect;

/**
 * Author : Shashikant Mhatre
 *
 * Created On : Dec 29, 2013
 *
 * Description :
 *
 * Note :
 */

public class MyOracle10gDialect extends Oracle10gDialect {
	public MyOracle10gDialect() {
        super();
    }
    protected void registerNumericTypeMappings() {
        super.registerNumericTypeMappings();
        registerColumnType( Types.DOUBLE, "float" );
    }
}
