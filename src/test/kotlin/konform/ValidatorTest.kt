package konform

import konform.path.PropertyPath
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ValidatorTest {

  data class NestedTestType(val testType: TestType)
  data class TestType(val input: String)

  @Test
  fun shouldValidateResult() {
    val validator = validate<TestType> {
      TestType::input {
        checks("should not be empty") { it.isNotBlank() }
      }
    }

    val result = validator.validate(TestType(""))
    assertThat(result.valid).isFalse()
    assertThat(result[TestType::input]).hasSize(1).containsExactly(
        ValidationError(PropertyPath(TestType::input), "should not be empty")
    )
  }

  @Test
  fun shouldNestValidationResult() {
    val validator = validate<NestedTestType> {
      NestedTestType::testType {
        TestType::input {
          checks("should not be empty") { it.isNotBlank() }
        }
      }
    }

    val result = validator.validate(NestedTestType(TestType("")))
    assertThat(result.valid).isFalse()
    assertThat(result[NestedTestType::testType, TestType::input]).hasSize(1).containsExactly(
        ValidationError(PropertyPath(NestedTestType::testType, TestType::input), "should not be empty")
    )
  }

  @Test
  fun shouldReturnValidWhenNoValidationIssues() {
    val validator = validate<NestedTestType> {
      NestedTestType::testType {
        TestType::input {
          checks("should not be empty") { it.isNotBlank() }
        }
      }
    }

    val result = validator.validate(NestedTestType(TestType("test")))
    assertThat(result.valid).isTrue()
    assertThat(result[NestedTestType::testType, TestType::input]).hasSize(0)
  }

}
