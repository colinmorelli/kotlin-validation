package konform.validators

import konform.ConstraintValidator
import konform.RootValidator
import konform.ValidationResult
import konform.context.ContextValue
import konform.context.ValidationContext
import konform.context.ValidatorContext
import konform.path.PropertyPath

class DefaultRootValidator<T>(private val validator: ConstraintValidator<T>): RootValidator<T> {

  override fun validate(value: T): ValidationResult<T> {
    return validate(value, ValidatorContext())
  }

  override fun validate(value: T, vararg context: ContextValue<*>): ValidationResult<T> {
    return validate(value, ValidatorContext(
        PropertyPath(),
        ValidationContext(*context)
    ))
  }

  override fun validate(value: T, context: ValidatorContext): ValidationResult<T> {
    return validator.validate(value, context)
  }

}
