# Milestone-07

## Contains code that is the result of the "composition over inheritance" refactoring:

The `AbstractPersonNameValidator` is cut from the `CollectorsItemValidator` inheritance tree.  
We now make use of composition rather than inheritance.

## Contains code that is the starting point for the "SRP" refactoring:

The domain classes have multiple reasons for modification; database mapping change, json response body object change and json request body object change.
We want to spread out the different responsibilities over different classes.