package konform.validators

import konform.ConstraintValidator
import konform.ValidationResult
import konform.context.ValidatorContext
import konform.path.PathSegment

class IterableValidator<R, T: Iterable<R>>(private val validator: ConstraintValidator<R>): ConstraintValidator<T> {
  override fun validate(value: T, context: ValidatorContext): ValidationResult<T> {
    return value.foldIndexed(ValidationResult(value, emptyList())) { index, result, child ->
      result.combineWith(
          validator.validate(child, context).prefix(PathSegment.Index(index.toLong()), value)
      )
    }
  }
}
