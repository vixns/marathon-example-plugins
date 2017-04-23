package mesosphere.marathon.example.plugin.javaauth;

import mesosphere.marathon.plugin.Group;
import mesosphere.marathon.plugin.PathId;
import mesosphere.marathon.plugin.RunSpec;
import mesosphere.marathon.plugin.auth.AuthorizedAction;
import mesosphere.marathon.plugin.auth.AuthorizedResource;
import mesosphere.marathon.plugin.auth.Authorizer;
import mesosphere.marathon.plugin.auth.Identity;
import mesosphere.marathon.plugin.http.HttpRequest;
import mesosphere.marathon.plugin.http.HttpResponse;
import org.apache.log4j.Logger;

public class JavaAuthorizer implements Authorizer {

    @Override
    public <Resource> boolean isAuthorized(Identity principal, AuthorizedAction<Resource> authorizedAction, Resource resource) {
        if (principal instanceof JavaIdentity) {
            JavaIdentity identity = (JavaIdentity) principal;
            Action action = Action.byAction(authorizedAction);

            if (resource instanceof Group) {
                return isAuthorized(identity, action, ((Group) resource).id());
            }
            if (resource instanceof RunSpec) {
                return isAuthorized(identity, action, ((RunSpec) resource).id());
            }

            // check the "operate" permission, should be improved to allow view/update operating rights.
            if (resource instanceof AuthorizedResource) {
                return identity.getUserPermissions().isOperator();
            }

            return resource instanceof PathId && isAuthorized(identity, action, (PathId) resource);
        }
        return false;
    }

    private boolean isAuthorized(JavaIdentity principal, Action action, PathId path) {
        log.debug(String.format("Authorizing user - %s for action - %s on Path - %s", principal.getName(),
                action.toString(), path.toString()));
        switch (action) {
            case CreateRunSpec:
            case CreateGroup:
                return principal.getUserPermissions().isAuthorized("create", path.toString());
            case UpdateRunSpec:
            case UpdateGroup:
                return principal.getUserPermissions().isAuthorized("update", path.toString());
            case DeleteRunSpec:
            case DeleteGroup:
                return principal.getUserPermissions().isAuthorized("delete", path.toString());
            case ViewRunSpec:
            case ViewGroup:
                return principal.getUserPermissions().isAuthorized("view", path.toString());
            default:
                return false;
        }
    }

    @Override
    public void handleNotAuthorized(Identity principal, HttpResponse response) {
        response.status(403);
        response.body("application/json", "{\"problem\": \"Not Authorized to perform this action!\"}".getBytes());
    }

    private static Logger log = Logger.getLogger(JavaAuthorizer.class.getName());
}
