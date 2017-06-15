# Milestone-08

## Contains code that is the result of the "SRP" refactoring:

The domain classes are now only responsible for the database mapping. We introduced separate -Result and -Form objects for response body and request body json mapping.

## Contains code that is the starting point for the "Separation of Concerns" refactoring:

The package structure is based upon the architectural layers; controller, service, repository. This structure results in low cohesion and high coupling. We want to refactor the package structure so that high cohesion / low coupling on package level is enforced.
