package konform

import konform.path.PathSegment
import konform.path.PropertyPath

data class ValidationError(val path: PropertyPath, val message: String) {
  fun prefix(segment: PathSegment): ValidationError {
    return ValidationError(path.prefix(segment), message)
  }
}
