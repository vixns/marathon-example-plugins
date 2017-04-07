# JavaAuth Example Plugin

This authorization plugin is based on HTTP basic authentication.
The purpose of this plugin is to make sure, that a Java based plugin works as well.

## Usage

See the [plugin configuration file](src/main/resources/mesosphere/marathon/example/plugin/javaauth/plugin-conf.json) and the [user permissions file](src/main/resources/mesosphere/marathon/example/plugin/javaauth/user-permissions-conf.json).

Marathon re-read the file specified by the `permissions-conf-file` config key every `permissions-conf-file-check-interval-seconds`.
This allows to update permissions without restarting marathon.

