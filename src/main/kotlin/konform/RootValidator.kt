package konform

import konform.context.ContextValue

interface RootValidator<T> : ConstraintValidator<T> {

  /**
   * Validate the provided value and return a result
   *
   * @param value the value to check
   * @return the result of validation
   */
  fun validate(value: T): ValidationResult<T>

  /**
   * Validate the provided value and return a result
   *
   * @param value the value to check
   * @return the result of validation
   */
  fun validate(value: T, vararg context: ContextValue<*>): ValidationResult<T>

}
