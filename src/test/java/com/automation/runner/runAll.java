package com.automation.runner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {
                "src/test/java/features/director/directorLogin.feature",
                "src/test/java/features/director/zCloseBrowser.feature",

        },
        glue = {
                "com.automation.test",
                "com.automation.director.login",
        }
//        ,
//        dryRun = true
//        tags = "",
//        plugin = {},
//        monochrome = true
)
public class runAll {
}
