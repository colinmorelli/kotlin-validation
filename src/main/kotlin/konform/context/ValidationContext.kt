package konform.context

class ValidationContext(vararg val context: ContextValue<*>) {

  private val contextMap = context.map { it.key to it.value }.toMap()

  operator fun <T : Any> get(key: ContextKey<T>): T {
    return contextMap[key] as T
  }

}
