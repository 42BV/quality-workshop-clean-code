# Milestone-02

## Contains code that is the result of the "naming conventions" refactoring:

All unittests have been fixed, but more important, all the tests have meaningful names now!  
When looking at the test method names, it's now clear what is tested without consulting the implementation.

## Contains code that is the starting point for the "DRY" refactoring:

In the `CollectorsItemService` there are 3 `createXXX` methods that share the same implementation.  
We are going to refactor the 3, so that the code duplication no longer exists.
