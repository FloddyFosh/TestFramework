Feature: 01 Test
  This features covers tests

  Scenario: 01 assignment - Customer adds product to his cart
    Given the homepage is loaded
    When the customer adds a phone Samsung galaxy s6 to its cart
    Then the Samsung galaxy s6 is added to the cart

  Scenario: 02 assignment - Customer adds products to his cart and checks total of cart
    Given the homepage is loaded
    When the customer adds a phone Iphone 6 32gb to its cart
    And the customer adds a phone Samsung galaxy s6 to its cart
    Then the total order sums to 1150 euro

  Scenario: 03 assignment - Customer adds phone and laptop to his cart
    Given the homepage is loaded
    When the customer adds a phone Iphone 6 32gb to its cart
    And the customer adds a laptop MacBook Pro to its cart
    Then the Iphone 6 32gb is added to the cart
    Then the MacBook Pro is added to the cart

  #Risk - Customers can't process an order successfully
  Scenario: 04 assignment - Customer processes an order successfully
    Given the homepage is loaded
    And the customer adds a phone Iphone 6 32gb to its cart
    When the customer places an order
    Then the order is processed successfully

  #Opdracht 5: Verwerk de klant gegevens in een object

  #Opdracht 6: Vraag Jochem een lege vraag en naar het weer
  Scenario: 06 assignment - Ask Jochem
    Given the Wiremock server is started
    And the Wiremockstub 1 is started
    When you ask Jochem an open question
    Then the Wiremock server is stopped