package konform.context

import konform.path.PropertyPath

data class ValidatorContext(
    val path: PropertyPath = PropertyPath(),
    val context: ValidationContext = ValidationContext()
)
