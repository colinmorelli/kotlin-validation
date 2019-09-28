package konform.dsl

import konform.ConstraintValidator
import konform.context.ValidatorContext
import konform.message.MessageSource
import konform.message.StaticMessageSource
import konform.validators.AllValidator
import konform.validators.IterableValidator
import konform.validators.PropertyAccessingValidator
import konform.validators.SimpleConstraintValidator
import konform.validators.UnwrappingValidator
import kotlin.reflect.KProperty1

class ValidationBuilder<T> internal constructor() {

  private val validators = mutableListOf<ConstraintValidator<T>>()

  /**
   * Creates a message source from translating the given message
   *
   * @param input the input message to translate
   * @return the translated message
   */
  fun t(input: String): MessageSource {
    return StaticMessageSource(input)
  }

  /**
   * Adds a validation constraint to the current builder
   *
   * @param validator adds a child validator to the list of validations
   */
  fun checks(validator: ConstraintValidator<T>) {
    validators.add(validator)
  }

  /**
   * Adds a validation constraint to the current builder
   *
   * @param message the message when the validation does not pass
   * @param validate the function to validate function
   */
  fun checks(message: String, validate: ValidatorContext.(T) -> Boolean) {
    checks(StaticMessageSource(message), validate)
  }

  /**
   * Adds a validation constraint to the current builder
   *
   * @param message the message when the validation does not pass
   * @param validate the function to validate function
   */
  fun checks(message: MessageSource, validate: ValidatorContext.(T) -> Boolean) {
    validators.add(SimpleConstraintValidator(message, validate))
  }

  /**
   * Validate a child property of the current scope
   *
   * @param builder builder block for the child property
   * @return the newly created validator
   */
  infix operator fun <R> KProperty1<T, R>.invoke(builder: ValidationBuilder<R>.() -> Unit) {
    validators.add(PropertyAccessingValidator(this, ValidationBuilder<R>().apply(builder).build()))
  }

  /**
   * Validate a child property of the current scope
   *
   * @param builder builder block for the child property
   * @return the newly created validator
   */
  infix fun <R> KProperty1<T, Iterable<R>>.onEach(builder: ValidationBuilder<R>.() -> Unit) {
    validators.add(
        PropertyAccessingValidator(
            this,
            IterableValidator(ValidationBuilder<R>().apply(builder).build())
        )
    )
  }

  /**
   * Validate a child property of the current scope
   *
   * @param builder builder block for the child property
   * @return the newly created validator
   */
  infix fun <R> KProperty1<T, R?>.ifPresent(builder: ValidationBuilder<R>.() -> Unit) {
    validators.add(
        PropertyAccessingValidator(
            this,
            UnwrappingValidator(ValidationBuilder<R>().apply(builder).build())
        )
    )
  }

  /**
   * Returns a constructed validator based on the provided validations
   */
  fun build(): ConstraintValidator<T> {
    return AllValidator(validators.toList())
  }

}
