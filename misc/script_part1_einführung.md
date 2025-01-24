# var

```kotlin
fun main() {
    val who = "World"
    println("Hello $who")
}
```

# val

```kotlin
fun main() {
  var who: String
  who = "World"

  println("Hello $who")
}
```

# NPE

-> who auf null setzen              -> error
-> String? setzen                   -> error
-> println("Hello ${who.length}")   -> error
-> println("Hello ${who?.length}")  -> run app

# classes

Erst Klasse ohne Body, dann Body hinzufügen

```kotlin
class Person(val name: String, val age: Int) {  // could be private!
    override fun toString(): String {
        return "Person(name='$name', age=$age)"
    }
}

fun main() {
  val p1 = Person("John", 30)
  val p2 = Person("John", 30)

  println("Hello $p1, $p2")
}
```

```kotlin
println("p1 is equal to p2: ${p1.equals(p2)}")  // -> false
println("p1 is equal to p2: ${p1 == p2}")       // -> false
```

-> data classes (toString(), equals, hashcode)

# Validierung mit init-Block

```kotlin
data class Person(val name: String, val age: Int) {
    init {
        require(age in 0..100) {
            "Age must be between 0 and 100"
        }
        require(name.isNotBlank()) {
            "Name must not be blank"
        }
    }
}
```

# Default-Werte

```kotlin
enum class Geschlecht {
    M, W, D
}
```
-> Enum hinzufügen und Compile-Error
-> dann Reienfolge der Attribute ändern

# Multiline Strings

```kotlin
fun readablePerson() = """
    Name: $name
    Age: $age
    Geschlecht: $geschlecht
""".trimIndent()
```