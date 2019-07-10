package com.dev;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;

/**
 * maven自定义插件规范
 * name为goal的name 需要使用goal来运行
 */
@Mojo(name = "cobraDev",defaultPhase = LifecyclePhase.PACKAGE)
public class CobraMojo extends AbstractMojo
{
    public void execute() throws MojoExecutionException, MojoFailureException
    {

        System.out.println("=========>>> 我的自定义插件");
    }
}
