package konform.validators

import konform.ConstraintValidator
import konform.ValidationError
import konform.ValidationResult
import konform.context.ValidatorContext
import konform.message.MessageSource
import konform.path.PropertyPath

class SimpleConstraintValidator<T>(private val message: MessageSource, private val validator: ValidatorContext.(T) -> Boolean): ConstraintValidator<T> {
  override fun validate(value: T, context: ValidatorContext): ValidationResult<T> {
    return ValidationResult(value, if (validator(context, value)) {
      emptyList()
    } else {
      listOf(ValidationError(PropertyPath(), message.acquire()))
    })
  }
}
