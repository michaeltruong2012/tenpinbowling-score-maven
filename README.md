# Code exercise: Ten Pin Bowling scoring.

This is a code exercise.

## Functional requirements

Write a java application that takes a string of space separated numbers from 0 to 10 and calculates what that would be as a score in ten pin bowling (hints on how to score in bowling are here: http://www.bowling2u.com/trivia/game/scoring.asp).  

If a final score cannot be determined from the input then method should return the "current" score (i.e. assumes any remaining bowls scored 0).  

Include unit tests and post code to a publicly accessible code repository.

Example inputs and outputs
- "1 2 3 4" -> 10
- "9 1 9 1" -> 29
- "1 1 1 1 10 1 1" -> 18
- "10 10 10 10 10 10 10 10 10 10 10 10" -> 300

## Technical requirements
- The code works (we will be going beyond the 4 examples bowls provided)
- It's tested (We will be looking at the extensiveness of their testing)
- Classes and methods are logically structured (please make it as easy to understand the code as possible) (Comments may be useful but we don't judge on this)
- They meet all the requirements

Also:
- Use of OO principles (Encapsulation, Polymorphism, Abstraction etc)
- Coupling and cohesion (These are not desired, including temporal coupling)
- Input validation
- Separation of concerns
- Appropriate use of access modifiers
- Superfluous code (Code which adds or does nothing for the solution is not desired)

# To verify the scores, refer to the website http://bit.ly/2kVCSTk.
