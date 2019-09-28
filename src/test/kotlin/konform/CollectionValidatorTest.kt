package konform

import konform.path.PropertyPath
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CollectionValidatorTest {

  data class ValidationContainer(val values: List<String>)

  @Test
  fun shouldValidateResult() {
    val validator = validate<ValidationContainer> {
      ValidationContainer::values onEach {
        checks("should not be empty") { it.isNotBlank() }
      }
    }

    val result = validator.validate(ValidationContainer(listOf("")))
    assertThat(result.valid).isFalse()
    assertThat(result[ValidationContainer::values, 0]).hasSize(1).containsExactly(
        ValidationError(PropertyPath(ValidationContainer::values, 0), "should not be empty")
    )
  }

}
