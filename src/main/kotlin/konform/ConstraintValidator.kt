package konform

import konform.context.ValidatorContext

interface ConstraintValidator<T> {

  /**
   * Validate the provided value and return a result
   *
   * @param value the value to check
   * @param context the current validation context
   * @return the result of validation
   */
  fun validate(value: T, context: ValidatorContext): ValidationResult<T>

}
