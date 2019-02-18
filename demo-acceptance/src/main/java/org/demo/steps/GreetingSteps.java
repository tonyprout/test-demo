package org.demo.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.demo.DemoClient;
import org.demo.factory.NameFactory;
import org.demo.holder.GreetingResponseHolder;
import org.demo.holder.NameHolder;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
public class GreetingSteps {

    private final NameFactory nameFactory;
    private final NameHolder nameHolder;
    private final DemoClient demoClient;
    private final GreetingResponseHolder responseHolder;

    public GreetingSteps(NameFactory nameFactory,
                         NameHolder nameHolder,
                         DemoClient demoClient,
                         GreetingResponseHolder responseHolder) {
        this.nameFactory = nameFactory;
        this.nameHolder = nameHolder;
        this.demoClient = demoClient;
        this.responseHolder = responseHolder;
    }

    @Given("^the user has a valid name$")
    public void theUserHasAValidName() {
        nameHolder.set(nameFactory.generateName());
    }

    @When("^the greeting endpoint is used with this name$")
    public void theGreetingEndpointIsUsedWithThisName() {
        final String greet = demoClient.greet(nameHolder.get());
        responseHolder.set(greet);
    }

    @Then("^a greeting is returned to the user$")
    public void aGreetingIsReturnedToTheUser() {
        assertThat(responseHolder.get(), equalTo(String.format("Hello %s", nameHolder.get())));
    }
}
