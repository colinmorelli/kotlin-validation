package konform.validators

import konform.ConstraintValidator
import konform.ValidationResult
import konform.context.ValidatorContext

class UnwrappingValidator<T>(private val validator: ConstraintValidator<T>): ConstraintValidator<T?> {
  override fun validate(value: T?, context: ValidatorContext): ValidationResult<T?> {
    return if (value == null) {
      ValidationResult(value)
    } else {
      validator.validate(value, context).nullable()
    }
  }
}
