# Milestone-04

## Contains code that is the result of the "useful comments" refactoring:

In `CollectorsItemValidator` the `validate` method is split up in several methods with descriptive names.  
No need for comments anymore!

## Contains code that is the starting point for the "law of demeter" refactoring:

The `CollectorsItemService` contains methods for csv import. In these methods the `CollectorsItemCsvReader` is used that returns a list of subclasses of `CollectorsItemCsvRecord`.  
Now we want to refactor the code so that `CollectorsItemService` does not have to know about specific csv record classes anymore.
