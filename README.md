# Milestone-05

## Contains code that is the result of the "law of demeter" refactoring:

We introduced the `CollectorsItemCsvReaderFacade` that provides an interface that shields callers from internal csv package classes.

## Contains code that is the starting point for the "open/closed" refactoring:

The `CollectorsItemValidator` class is not open for extension and closed for modification; each time a new subtype of `CollectorsItem` is introduced the implementation has to be changed. Also, when the validation of a subtype changes, the `CollectorsItemValidator` has to be modified.
We are going to refactor the code so that when introducing a new subtype of `CollectorsItem`, the existing implementation does not have to change...