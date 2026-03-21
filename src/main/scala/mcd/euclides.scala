package cl.uchile.dcc
package mcd

/*
El siguiente import es necesario sí quieren leer números enteros desde la consola.
Pueden leer un entero de la siguiente forma:

  val a = readInt()
  
y luego usarlo como deseen

  val res = a + 5
  println(s"El resultado del número ingresado más 5 es $res")
  
*/
import scala.io.StdIn.readInt

@main def euclidesInteractivo(): Unit = {
  // Inicio de la zona donde deben editar el código

/** Returns the Greatest Common Divider of two integers using Euclid's algorithm
 * */

//GCD Logic
 def mcd(a: Int, b: Int): Int =

   if (b == 0) {
      a
    }
   else if (a < 0 && b > 0) {
      mcd(-a, b)
    }
   else if (a > 0 && b < 0) {
      -mcd(a, -b)
    }
   else if (a < 0 && b < 0) {
      mcd(-a, -b)
    }
   else mcd(b, a % b)

  //Console Cycle
  while (true) {

    print("Ingrese el primer número (0 para salir): ")
    val a = readInt()
    if (a == 0) {
      return
    }

    print("Ingrese el segundo número: ")
    val b = readInt()
    println(s"MCD de $a y $b: ${mcd(a, b)}")

  }
}
  // Fin de la zona donde deben editar el código

