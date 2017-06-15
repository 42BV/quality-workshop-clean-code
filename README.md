# Milestone-06

## Contains code that is the result of the "open/closed" refactoring:

The `CollectorsItemValidator` inheritance tree is now open for extension and closed for modification; each time a new subtype of `CollectorsItem` is introduced the implementation of the hierarchy does not change. Also, when the validation of a subtype changes, only this subtype has to be modified.

## Contains code that is the starting point for the "composition over inheritance" refactoring:

For a `Book` and an `Album` the writer and artist have to be validated. To comply with the DRY principle an `AbstractPersonNameValidator` is added to the `CollectorsItemValidator` inheritance tree.  
However, we want to make use of composition rather than inheritance when aiming for code reuse...
