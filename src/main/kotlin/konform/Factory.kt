package konform

import konform.dsl.ValidationBuilder
import konform.validators.DefaultRootValidator

fun <T> validate(builder: ValidationBuilder<T>.() -> Unit): RootValidator<T> {
  return DefaultRootValidator(
      ValidationBuilder<T>().apply(builder).build()
  )
}
