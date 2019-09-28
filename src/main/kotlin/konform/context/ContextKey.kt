package konform.context

import kotlin.reflect.KClass

data class ContextKey<T: Any>(val type: KClass<T>) {

  /**
   * Create a new context entry for validation
   *
   * @param value the value to use in the context
   */
  infix fun withValue(value: T): ContextValue<T> {
    return ContextValue(this, value)
  }

}

inline fun <reified T: Any> createContextKey(): ContextKey<T> {
  return ContextKey(T::class)
}
