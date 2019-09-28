# Kotlin Validation

Clean validation library for Kotlin with a convenient DSL for creating validators.

---

Simple Example

```
val validateUser = validate<User> {
    User::firstName {
        notBlank()
    }
    
    User::lastName {
        notBlank()
    }
}

val result = validateUser(User("", ""))
result.valid // false
result[User::firstName] // listOf(ValidationError(PropertyPath("firstName"), "must not be blank"))
```
