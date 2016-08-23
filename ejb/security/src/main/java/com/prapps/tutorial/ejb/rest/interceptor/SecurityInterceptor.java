package com.prapps.tutorial.ejb.rest.interceptor;

import java.security.Principal;
import java.util.Map;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import org.jboss.logging.Logger;
/**
 * The server side security interceptor responsible for handling any security identity propagated from the client.
 * This interceptor is configured in jboss-ejb3.xml of the application package.
 * 
 * @author <a href="mailto:darran.lofthouse@jboss.com">Darran Lofthouse</a>
 */
public class SecurityInterceptor {

    private static final Logger LOG = Logger.getLogger(SecurityInterceptor.class);

    static final String DELEGATED_USER_KEY = "TestDelegationUser";

    @AroundInvoke
    public Object aroundInvoke(final InvocationContext invocationContext) throws Exception {
    	LOG.info("invoking SecurityInterceptor->aroundInvoke");
        Principal desiredUser = null;
       // RealmUser connectionUser = null;

        Map<String, Object> contextData = invocationContext.getContextData();
        LOG.info(">>>>>>>>>> contextData " + contextData);
        if (contextData.containsKey(DELEGATED_USER_KEY)) {
            desiredUser = new SimplePrincipal((String) contextData.get(DELEGATED_USER_KEY));
            LOG.trace("desiredUser " + desiredUser);

           /* Connection con = SecurityActions.remotingContextGetConnection();

            if (con != null) {
                UserInfo userInfo = con.getUserInfo();
                if (userInfo instanceof SubjectUserInfo) {
                    SubjectUserInfo sinfo = (SubjectUserInfo) userInfo;
                    for (Principal current : sinfo.getPrincipals()) {
                        if (current instanceof RealmUser) {
                            connectionUser = (RealmUser) current;
                            break;
                        }
                    }
                }

            } else {
                throw new IllegalStateException("Delegation user requested but no user on connection found.");
            }*/
        }

       /* SecurityContext cachedSecurityContext = null;
        boolean contextSet = false;
        try {
            if (desiredUser != null && connectionUser != null
                    && (desiredUser.getName().equals(connectionUser.getName()) == false)) {
                // The final part of this check is to verify that the change does actually indicate a change in user.
                try {
                    // We have been requested to switch user and have successfully identified the user from the connection
                    // so now we attempt the switch.
                    cachedSecurityContext = SecurityActions.securityContextSetPrincipalInfo(desiredUser,
                            new OuterUserCredential(connectionUser));
                    logger.info(">>>>>>>>>>>>> Switch users ");
                    // keep track that we switched the security context
                    contextSet = true;
                    SecurityActions.remotingContextClear();
                } catch (Exception e) {
                    logger.error("Failed to switch security context for user", e);
                    // Don't propagate the exception stacktrace back to the client for security reasons
                    throw new EJBAccessException("Unable to attempt switching of user.");
                }
            }

        } finally {
            if (contextSet) {
                SecurityActions.securityContextSet(cachedSecurityContext);
            }
        }*/
        return invocationContext.proceed();
    }

    private static class SimplePrincipal implements Principal {

        private final String name;

        private SimplePrincipal(final String name) {
            if (name == null) {
                throw new IllegalArgumentException("name can not be null.");
            }
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        public int hashCode() {
            return name.hashCode();
        }

        @Override
        public boolean equals(Object other) {
            return other instanceof SimplePrincipal && equals((SimplePrincipal) other);
        }

        public boolean equals(SimplePrincipal other) {
            return this == other || other != null && name.equals(other.name);
        }

    }
}