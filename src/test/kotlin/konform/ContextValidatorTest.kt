package konform

import konform.context.createContextKey
import konform.path.PropertyPath
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ContextValidatorTest {

  private val TestContextKey = createContextKey<Int>()
  private data class ValidationContainer(val value: Int)

  @Test
  fun shouldValidateResult() {
    val validator = validate<ValidationContainer> {
      ValidationContainer::value {
        checks("must be greater than context value") { it > context[TestContextKey] }
      }
    }

    val result = validator.validate(
        ValidationContainer(1),
        TestContextKey withValue 2
    )

    assertThat(result.valid).isFalse()
    assertThat(result[ValidationContainer::value]).hasSize(1).containsExactly(
        ValidationError(PropertyPath(ValidationContainer::value), "must be greater than context value")
    )
  }

}
