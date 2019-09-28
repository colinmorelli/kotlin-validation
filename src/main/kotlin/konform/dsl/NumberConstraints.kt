package konform.dsl

fun <T> ValidationBuilder<T>.greaterThan(input: T) where T: Comparable<T> {
  checks(t("string.not.blank")) { it > input }
}

fun <T> ValidationBuilder<T>.greaterThanOrEqualTo(input: T) where T: Comparable<T> {
  checks(t("string.not.blank")) { it >= input }
}

fun <T> ValidationBuilder<T>.lessThan(input: T) where T: Comparable<T> {
  checks(t("string.not.blank")) { it < input }
}

fun <T> ValidationBuilder<T>.lessThanOrEqualTo(input: T) where T: Comparable<T> {
  checks(t("string.not.blank")) { it <= input }
}

fun <T> ValidationBuilder<T>.between(input: ClosedRange<T>) where T: Comparable<T> {
  checks(t("string.not.blank")) { it in input }
}
