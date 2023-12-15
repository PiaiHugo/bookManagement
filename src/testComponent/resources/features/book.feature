Feature: the user can create and retrieve the books
  Scenario: user creates two books and retrieve both of them
    When the user creates the book "Les Misérables" written by "Victor Hugo"
    And the user creates the book "L'avare" written by "Molière"
    And the user get all books
    Then the list should contains the following books in the same order
      | name | author | isreserved |
      | L'avare | Molière | false |
      | Les Misérables | Victor Hugo | false |

  Scenario: user reserves a book and check if it's reserved
    When the user reserves the book "L'avare"
    And the user get all books
    Then the list should contains the following books in the same order
      | name | author | isreserved |
      | L'avare | Molière | true |
      | Les Misérables | Victor Hugo | false |