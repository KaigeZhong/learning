package sample.plugin;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;

@Mojo(name = "sayHey")
public class GreetingMojo2 extends AbstractMojo {
    public void execute() throws MojoExecutionException, MojoFailureException {
        getLog().info(
                "######################################################" +
                        "hey" +
                        "######################################################");
    }
}
