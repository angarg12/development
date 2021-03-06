/*******************************************************************************
 *
 *  Copyright FUJITSU LIMITED 2017
 *
 *  Creation Date: 17.11.15 12:09
 *
 ******************************************************************************/
package org.oscm.database;

import java.net.URL;

import org.oscm.setup.DatabaseVersionInfo;

public class SchemaUpgrade_02_08_13_to_02_08_14_IT extends
        SchemaUpgradeTestBase {

    public SchemaUpgrade_02_08_13_to_02_08_14_IT() {
        super(new DatabaseVersionInfo(2, 8, 13), new DatabaseVersionInfo(2, 8,
                14));
    }

    @Override
    protected URL getSetupDataset() {
        return getClass().getResource("/setup_02_08_13_to_02_08_14.xml");
    }

    @Override
    protected URL getExpectedDataset() {
        return getClass().getResource("/expected_02_08_13_to_02_08_14.xml");
    }

}
