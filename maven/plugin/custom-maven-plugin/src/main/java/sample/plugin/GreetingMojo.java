package sample.plugin;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

/**
 * Says "Hi" to the user.
 */
@Mojo(name = "sayHi")
public class GreetingMojo extends AbstractMojo {
    @Parameter(property = "configKey")
    private String configKey;
    @Parameter
    private String config;
    @Parameter(property = "configKeyWithDefault", defaultValue = "configDefaultValue")
    private String configKeyWithDefault;

    public void execute() throws MojoExecutionException {
        getLog().info(
                "######################################################" +
                        "Hello, world." +
                        "######################################################");
        logParameter();
    }

    private void logParameter() {
        getLog().info(
                "######################################################" +
                        "configKey: " + configKey + ", " +
                        "config: " + config + ", " +
                        "configKeyWithDefault: " + configKeyWithDefault + ", " +
                        "######################################################");
    }
}