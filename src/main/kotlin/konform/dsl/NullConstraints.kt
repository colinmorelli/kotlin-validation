package konform.dsl

import konform.validators.UnwrappingValidator

fun <T> ValidationBuilder<T?>.ifPresent(builder: ValidationBuilder<T>.() -> Unit) {
  checks(UnwrappingValidator(ValidationBuilder<T>().apply(builder).build()))
}
