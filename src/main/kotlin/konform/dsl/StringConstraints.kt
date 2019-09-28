package konform.dsl

fun ValidationBuilder<String>.notBlank() {
  checks(t("string.not.blank")) { it.isNotBlank() }
}
