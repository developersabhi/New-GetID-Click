@Director
Feature: Director login with correct username and password
  Here we want to login the director panel using correct username and password.

  @Director @before
  Scenario:Login the director panel.
    Given User log in to provider URL and is already present at the website list page.