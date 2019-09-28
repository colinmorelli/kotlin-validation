package konform.path

data class PropertyPath(private val segments: List<PathSegment> = emptyList()) {

  constructor(vararg segments: Any): this(segments.map { createPathSegment(it) })

  private val formatted by lazy {
    val builder = StringBuilder()

    for ((index, segment) in segments.withIndex()) {
      when (segment) {
        is PathSegment.Named -> {
          if (index > 0) {
            builder.append(".")
          }

          builder.append(segment.named)
        }
        is PathSegment.Index -> builder.append("[").append(index).append("]")
      }
    }

    builder.toString()
  }

  fun prefix(segment: PathSegment): PropertyPath {
    return PropertyPath(listOf(segment) + segments)
  }

  override fun toString(): String {
    return formatted
  }

}
