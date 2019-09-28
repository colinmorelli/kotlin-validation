package konform.context

data class ContextValue<T: Any>(
    val key: ContextKey<T>,
    val value: T
)
