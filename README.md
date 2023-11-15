# Demo Project To Show Hibernate Envers Issue with @OrderBy in Abstract Classes

This project highlights an issue with Hibernate Envers when using the `@OrderBy` annotation in abstract classes with `@OneToMany` relationships.

## Problem Description

In the `University` class, there is a `List<Human>` field named `humanList` with the following annotations:

```java
@OneToMany(mappedBy = "university", cascade = CascadeType.ALL, orphanRemoval = true)
@OrderBy("orderId")
private List<Human> humanList = new ArrayList<>();
```
The problem arises when @OrderBy("orderId") is used. It seems that Hibernate Envers has issues working with this annotation and abstract classes.
Removing @OrderBy("orderId") resolves the issue, but this may not be a suitable solution in cases where ordering is crucial.
