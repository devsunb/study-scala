// 주석
/* 주석 */
/** 문서화 주석 */
object MyModule {
    def abs(n: Int): Int =
        if (n < 0) -n
        else n

    private def formatAbs(x: Int) = {
        val msg = "The absolute value of %d is %d"
        msg.format(x, abs(x))
    }


    def main(args: Array[String]): Unit =
        println(formatAbs(-42))
}