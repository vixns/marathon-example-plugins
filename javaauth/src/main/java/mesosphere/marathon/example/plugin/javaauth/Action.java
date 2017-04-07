package mesosphere.marathon.example.plugin.javaauth;

import mesosphere.marathon.plugin.auth.AuthorizedAction;

/**
 * Enumeration for handling AuthorizedActions more easily in Java.
 */
public enum Action {

    CreateRunSpec(mesosphere.marathon.plugin.auth.CreateRunSpec$.MODULE$),
    UpdateRunSpec(mesosphere.marathon.plugin.auth.UpdateRunSpec$.MODULE$),
    DeleteRunSpec(mesosphere.marathon.plugin.auth.DeleteRunSpec$.MODULE$),
    ViewRunSpec(mesosphere.marathon.plugin.auth.ViewRunSpec$.MODULE$),
    ViewResource(mesosphere.marathon.plugin.auth.ViewResource$.MODULE$),
    UpdateResource(mesosphere.marathon.plugin.auth.UpdateResource$.MODULE$),
    CreateGroup(mesosphere.marathon.plugin.auth.CreateGroup$.MODULE$),
    UpdateGroup(mesosphere.marathon.plugin.auth.UpdateGroup$.MODULE$),
    DeleteGroup(mesosphere.marathon.plugin.auth.DeleteGroup$.MODULE$),
    ViewGroup(mesosphere.marathon.plugin.auth.ViewGroup$.MODULE$);

    public static Action byAction(AuthorizedAction<?> action) {
        for (Action a : values()) {
            if (a.action.equals(action)) {
                return a;
            }
        }
        throw new IllegalArgumentException("Unknown Action: " + action);
    }

    private final AuthorizedAction<?> action;
    Action(AuthorizedAction<?> action) {
        this.action = action;
    }
}
