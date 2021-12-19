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
fun Monte_Carlo(n:Int,dim:Int):Double{
    var k = 0.0

    var dot = DotDouble(dim)
    for(i in 0..n){
        for(j in 0 until dim){
            dot[j] = (2 * Random.nextDouble())
        }
        if (dot.compare(1.0)) {
            k += 1.0
        }

    }
    return((k/n.toDouble())*(2.0).pow(dim.toDouble()))
}




fun main() {
    println("Введите размерность пространства:")
    var dimensions = arrayListOf<Int>()
    for (i in 2..10) dimensions.add(i)
    var valumes = arrayListOf<Double>()
    /*var input = readLine()
    var dim:Int = 0

    if (input != null) {
        dim = input.toInt()
        if (dim < 2 || dim > 10) {
            error("Данная размерность не поддерживается в текущей версии программы")
        }
    }*/
    var dim = 0
    var sum = 0.0

    val n = 100000
    val m = 1000
    val dots = Array<Int>(100){0}
    val deviation = arrayListOf<Double>()
    val gen = Random
    val xs2 = arrayListOf<Double>() // цифра два в названии массива размерность пространства
    val ys2 = arrayListOf<Double>()
    var flag:Int = 0 //счетчик
    var k:Int = 0 //количество точек попавших в круг
    dots[0]=100
    for (i in 1..99){
        dots[i] = dots[i-1]+100
    }
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

        k = 0
    }

    for (i in dots){
        for (j in 0 until m ){

            sum += (Monte_Carlo(i,3)- (4.0/3.0)*PI).pow(2)

        }


        deviation.add(sqrt(sum/m.toDouble()))

        sum = 0.0
    }


    var valume:Double = (k.toDouble()/n.toDouble())*(2.0).pow(dim.toDouble()) // 2^dim  - площадь dim мерного квадрата

    var data = mapOf<String,Any>("x" to dimensions,"y" to valumes)
    var data1 = mapOf<String,Any>("dots" to dots,"deviation" to deviation)
    val fig1 = letsPlot(data1) + geomPoint(color = "blue",size = 2.0){x = "dots";y = "deviation"}
    val fig = letsPlot(data)+ geomPoint(color = "dark-green", size = 3.0){x = "x";y = "y"}
    ggsave(fig,"plot.png")
    ggsave(fig1,"DeviationPlotForDim3.png")
}
