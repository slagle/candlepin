Feature: Register a Consumer
    In order to make subsequent calls to Candlepin
    As a Consumer
    I want to be able to obtain a valid Identity Certificate

    Background:
        Given an owner admin "bill"

    Scenario: Identity Certificate is Generated
        Given I am logged in as "bill"
        And I register a consumer "someconsumer"
        Then my consumer should have an identity certificate

    Scenario: Correct UID on Identity Certificate
        Given I am logged in as "bill"
        And I register a consumer "some_box"
        Then the "CN" on my identity certificate's subject is my consumer's UUID

    Scenario: Correct CN on Identity Certificate
        Given I am logged in as "bill"
        And I register a consumer "kitt_the_car"
        Then the consumers name in the certificate is "kitt_the_car"

    Scenario: Correct OU on Identity Certificate
        Given I am logged in as "bill"
        And I register a consumer "foo"
        Then the consumers name in the certificate is "foo"

    Scenario: Register with explicit UUID
        Given I am logged in as "bill"
        When I register a consumer "my_machine" with uuid "special_uuid"
        Then the "CN" on my identity certificate's subject is "special_uuid"
        Then the consumers name in the certificate is "my_machine"

    Scenario: Reuse a UUID during registration
        Given I am logged in as "bill"
        When I register a consumer "my_machine" with uuid "special_uuid"
        Then registering another consumer with uuid "special_uuid" causes a bad request

    Scenario: Getting a consumer that does not exist should return a Not Found
        Given I am logged in as "bill"
        Then searching for a consumer with uuid "jar_jar_binks" causes a not found