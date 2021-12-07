import java.lang.Exception
import kotlin.random.Random
import kotlin.math.*
import jetbrains.letsPlot.export.ggsave
import jetbrains.letsPlot.geom.geomLine
import jetbrains.letsPlot.geom.geomPoint
import jetbrains.letsPlot.letsPlot


class DotDouble(var dimension:Int =2) {

    var coordinates = Array(dimension){0.0}

    operator fun set(index:Int,value:Double){ coordinates[index] = value}
    operator fun plus(other: DotDouble):DotDouble{
        var x = DotDouble(dimension)
        if (other.coordinates.size != coordinates.size)
        {throw Exception("Different sizes of dots")}else {
            for (i in 0 until coordinates.size) {
                x[i] = coordinates[i] + other.coordinates[i]
            }
        }
        return x
    }
    fun printdot()
    //выводит координаты точки на экран
    {
        var buffer:String = ""
        buffer += "("
        for (i in coordinates)
        {
            buffer += "$i,"
        }
        buffer = buffer.dropLast(1)
        buffer += ")"
        print(buffer)
    }
    fun compare(x:Double):Boolean
    //сравнивает модуль радиус-вектора к данной точке с числом и возвращает false, если модуль больше числа
    {
        var flag:Boolean = true
        var sum:Double = 0.0
        for (i in coordinates) sum += (i-1)*(i-1)
        if (sum > x*x) flag = false
        return  flag
    }
}


fun main() {
    println("Введите размерность пространства:")
    var dimensions = arrayListOf<Int>()
    for (i in 2..10) dimensions.add(i)
    var valumes = arrayListOf<Double>()
    var dim = 0
    val n = 100000
    val gen = Random
    val xs2 = arrayListOf<Double>() // цифра два в названии массива размерность пространства
    val ys2 = arrayListOf<Double>()
    var flag:Int = 0 //счетчик
    var k:Int = 0 //количество точек попавших в круг
    for (a in 2..10) {
        dim = a
        var dot = DotDouble(dim)

        for (i in 0..n) {
            for (j in 0 until dim) {
                dot[j] = (2 * gen.nextDouble())
            }
            if (dot.compare(1.0)) {
                k += 1
            }
            flag += 1
        }
        valumes.add((k.toDouble()/n.toDouble())*(2.0).pow(dim.toDouble()))
        println(valumes[a-2])
        k = 0
    }
    

var data = mapOf<String,Any>("x" to dimensions,"y" to valumes)
val fig = letsPlot(data)+ geomPoint(color = "dark-green", size = 3.0){x = "x";y = "y"}
    ggsave(fig,"plot.png")
}
