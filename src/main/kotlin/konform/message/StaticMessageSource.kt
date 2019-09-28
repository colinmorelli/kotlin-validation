package konform.message

import konform.context.ValidatorContext

class StaticMessageSource(private val message: String): MessageSource {

  override fun acquire(context: ValidatorContext): String {
    return message
  }

}
