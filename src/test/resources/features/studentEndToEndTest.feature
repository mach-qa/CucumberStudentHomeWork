Feature: Add, Edit and Remove Student object
  Scenario: Student is editable and removable
    Given new Students is registered
    When details of student are saved
    And middle name of student is changed
    And checks that middle name is changed
    And delete student
    Then verify that student does not exist