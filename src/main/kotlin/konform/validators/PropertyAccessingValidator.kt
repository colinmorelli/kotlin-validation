package konform.validators

import konform.ConstraintValidator
import konform.ValidationResult
import konform.context.ValidatorContext
import konform.path.PathSegment
import kotlin.reflect.KProperty1

class PropertyAccessingValidator<T, R>(private val property: KProperty1<T, R>, private val validator: ConstraintValidator<R>): ConstraintValidator<T> {
  override fun validate(value: T, context: ValidatorContext): ValidationResult<T> {
    return validator.validate(property.get(value), context).prefix(PathSegment.Named(property.name), value)
  }
}
