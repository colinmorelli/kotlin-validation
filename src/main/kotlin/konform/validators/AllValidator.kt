package konform.validators

import konform.ConstraintValidator
import konform.ValidationResult
import konform.context.ValidatorContext

class AllValidator<T>(private val validators: List<ConstraintValidator<T>>): ConstraintValidator<T> {
  override fun validate(value: T, context: ValidatorContext): ValidationResult<T> {
    return validators.fold(ValidationResult(value)) { result, validator ->
      return result.combineWith(validator.validate(value, context))
    }
  }
}
