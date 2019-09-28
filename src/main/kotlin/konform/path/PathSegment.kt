package konform.path

import kotlin.reflect.KCallable

sealed class PathSegment {
  data class Index(val index: Long): PathSegment()
  data class Named(val named: String): PathSegment()
}

fun createPathSegment(input: Any): PathSegment {
  return when (input) {
    is KCallable<*> -> PathSegment.Named(input.name)
    is Int -> PathSegment.Index(input.toLong())
    is Long -> PathSegment.Index(input)
    else -> PathSegment.Named(input.toString())
  }
}
