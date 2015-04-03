/*
 * Copyright (c) 2013 EMBL - European Bioinformatics Institute
 * Licensed under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */

package uk.ac.ebi.fgpt.lode.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.ac.ebi.fgpt.lode.utils.DatasourceProvider;

//import virtuoso.jdbc4.VirtuosoDataSource;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author Daniel Davis
 * @date 04/02/2015
 * U.S. National Library of Medicine
 */
public class OracleDatasourceProvider implements DatasourceProvider {
    private Logger logger = LoggerFactory.getLogger(getClass());

    private OracleDataSource source = null;

    public OracleDatasourceProvider() {
        // Get the DataSource using JNDI
        if (source == null) {
            try {
                Context context = (Context) (new InitialContext()).lookup("java:comp/env");
                virtuosoSource = (VirtuosoDataSource) context.lookup("jdbc/oraclerdf");
            }
            catch (NamingException e) {
                throw new IllegalStateException("Oracle JNDI datasource not configured: " + e.getMessage());
            }
        }
    }

    public DataSource getDataSource() throws SQLException {
        return source;
    }
}