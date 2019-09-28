package konform

import konform.dsl.ifPresent
import konform.dsl.notBlank
import konform.path.PropertyPath
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class NullableValidatorTest {

  data class ValidationContainer(val value: String?)

  @Test
  fun shouldNotFailWhenNull() {
    val validator = validate<ValidationContainer> {
      ValidationContainer::value {
        ifPresent {
          notBlank()
        }
      }
    }

    val result = validator.validate(ValidationContainer(null))
    assertThat(result.valid).isTrue()
    assertThat(result[ValidationContainer::value]).hasSize(0)
  }

  @Test
  fun shouldFailWhenNonNull() {
    val validator = validate<ValidationContainer> {
      ValidationContainer::value {
        ifPresent {
          notBlank()
        }
      }
    }

    val result = validator.validate(ValidationContainer(""))
    assertThat(result.valid).isFalse()
    assertThat(result[ValidationContainer::value]).hasSize(1).containsExactly(
        ValidationError(PropertyPath(ValidationContainer::value), "must not be blank")
    )
  }

}
