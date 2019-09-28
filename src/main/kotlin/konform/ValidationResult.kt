package konform

import konform.path.PathSegment
import konform.path.PropertyPath
import konform.path.createPathSegment

data class ValidationResult<T>(val value: T, val errors: List<ValidationError> = emptyList()) {

  val valid = errors.isEmpty()

  fun combineWith(other: ValidationResult<T>): ValidationResult<T> {
    return ValidationResult(value, errors + other.errors)
  }

  fun <R> prefix(segment: PathSegment, value: R): ValidationResult<R> {
    return ValidationResult(value, errors.map { it.prefix(segment) })
  }

  fun nullable(): ValidationResult<T?> {
    return ValidationResult(value, errors)
  }

  operator fun get(vararg props: Any): List<ValidationError> {
    val path = PropertyPath(props.map {
      createPathSegment(it)
    })

    return errors.filter {
      it.path == path
    }
  }

}
