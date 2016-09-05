/*******************************************************************************
 *                                                                              
 *  Copyright FUJITSU LIMITED 2016                                             
 *                                                                                                                                 
 *  Creation Date: 07.06.2013                                                      
 *                                                                              
 *******************************************************************************/

package org.oscm.ui.filter;

import java.util.StringTokenizer;

import org.oscm.internal.intf.TenantService;
import org.oscm.internal.types.exception.NotExistentTenantException;
import org.oscm.internal.vo.VOTenant;
import org.oscm.types.constants.Configuration;
import org.oscm.internal.intf.ConfigurationService;
import org.oscm.internal.types.enumtypes.AuthenticationMode;
import org.oscm.internal.types.enumtypes.ConfigurationKey;
import org.oscm.internal.vo.VOConfigurationSetting;

import static org.oscm.internal.types.exception.NotExistentTenantException.Reason.MISSING_TENANT_PARAM;
import static org.oscm.internal.types.exception.NotExistentTenantException.Reason.TENANT_NOT_FOUND;

/**
 * @author stavreva
 * 
 */
public class AuthenticationSettings {

    private String authenticationMode;
    private String issuer;
    private String identityProviderHttpMethod;
    private String identityProviderURL;
    private String identityProviderURLContextRoot;
    private TenantService tenantService;
    private String signingKeystorePass;
    private String signingKeyAlias;
    private String signingKeystore;
    private String logoutURL;

    public AuthenticationSettings(TenantService tenantService, ConfigurationService cfgService) {
        this.tenantService = tenantService;
        authenticationMode = getConfigurationSetting(cfgService,
                ConfigurationKey.AUTH_MODE);
    }

    String getConfigurationSetting(ConfigurationService cfgService,
            ConfigurationKey key) {
        VOConfigurationSetting voConfSetting = cfgService
                .getVOConfigurationSetting(key, Configuration.GLOBAL_CONTEXT);
        String setting = null;
        if (voConfSetting != null) {
            setting = voConfSetting.getValue();
        }
        return setting;
    }

    /**
     * The URL must be set to lower case, because in the configuration settings,
     * it can be given with upper case and matching will not work.
     */
    String getContextRoot(String idpURL) {
        String contextRoot = null;
        if (idpURL != null && idpURL.length() != 0) {
            StringTokenizer t = new StringTokenizer(idpURL, "/");
            if (t.countTokens() >= 3) {
                contextRoot = t.nextToken().toLowerCase() + "//"
                        + t.nextToken().toLowerCase() + "/"
                        + t.nextToken().toLowerCase();
            }
        }
        return contextRoot;
    }

    public boolean isServiceProvider() {
        return AuthenticationMode.SAML_SP.name().equals(authenticationMode);
    }

    public boolean isInternal() {
        return AuthenticationMode.INTERNAL.name().equals(authenticationMode);
    }

    public String getIssuer(String tenantID) throws NotExistentTenantException {
        if (issuer == null) {
            init(tenantID);
        }
        return issuer;
    }

    private void init(String tenantID) throws NotExistentTenantException {
        if (tenantID == null) {
            throw new NotExistentTenantException(MISSING_TENANT_PARAM);
        }
        VOTenant tenant = tenantService.findByTkey(tenantID);
        if (tenant == null) {
            throw new NotExistentTenantException(TENANT_NOT_FOUND);
        }
        issuer = tenant.getIssuer();
        identityProviderURL = tenant.getIDPURL();
        identityProviderHttpMethod = tenant.getIdpHttpMethod();
        identityProviderURLContextRoot = getContextRoot(identityProviderURL);
        signingKeystorePass = tenant.getSigningKeystorePass();
        signingKeyAlias = tenant.getSigningKeyAlias();
        signingKeystore = tenant.getSigningKeystore();
        logoutURL = tenant.getLogoutURL();
    }

    public String getIdentityProviderURL(String tenantID) throws NotExistentTenantException {
        if (identityProviderURL == null) {
            init(tenantID);
        }
        return identityProviderURL;
    }

    public String getIdentityProviderURLContextRoot(String tenantID) throws NotExistentTenantException {
        if (identityProviderURLContextRoot == null) {
            init(tenantID);
        }
        return identityProviderURLContextRoot;
    }

    public String getIdentityProviderHttpMethod(String tenantID) throws NotExistentTenantException {
        if (identityProviderHttpMethod == null) {
            init(tenantID);
        }
        return identityProviderHttpMethod;
    }

    public String getSigningKeystorePass(String tenantID) throws NotExistentTenantException {
        if (signingKeystorePass == null) {
            init(tenantID);
        }
        return signingKeystorePass;
    }

    public String getSigningKeyAlias(String tenantID) throws NotExistentTenantException {
        if (signingKeyAlias == null) {
            init(tenantID);
        }
        return signingKeyAlias;
    }

    public String getSigningKeystore(String tenantID) throws NotExistentTenantException {
        if (signingKeystore == null) {
            init(tenantID);
        }
        return signingKeystore;
    }

    public String getLogoutURL(String tenantID) throws NotExistentTenantException {
        if (logoutURL == null) {
            init(tenantID);
        }
        return logoutURL;
    }
}
