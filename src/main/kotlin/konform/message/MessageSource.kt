package konform.message

import konform.context.ValidatorContext

interface MessageSource {

  fun acquire(context: ValidatorContext): String

}
