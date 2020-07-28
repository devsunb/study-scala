sealed trait List[+A]
case object Nil extends List[Nothing]
case class Cons[+A](head: A, tail: List[A]) extends List[A]

object List {
    def apply[A](as: A*): List[A] = {
        @annotation.tailrec
        def go[A](as: Seq[A], l: List[A]): List[A] = {
            if (as.isEmpty) l
            else go(as.tail, Cons(as.head, l))
        }
        go(as.reverse, Nil)
    }

    def sum(ints: List[Int]): Int = ints match {
        case Nil => 0
        case Cons(x, xs) => x + sum(xs)
    }

    def product(ds: List[Double]): Double = ds match {
        case Nil => 1.0
        case Cons(x, xs) => x * product(xs)
    }

    def head[A](l: List[A]): A = l match {
        case Nil => sys.error("head of empty list")
        case Cons(h, _) => h
    }

    def tail[A](l: List[A]): List[A] = l match {
        case Nil => sys.error("tail of empty list")
        case Cons(_, t) => t
    }

    def setHead[A](l: List[A], h: A): List[A] = l match {
        case Nil => sys.error("setHead on empty list")
        case Cons(_, t) => Cons(h, t)
    }

    def drop[A](l: List[A], n: Int): List[A] = {
        if (n <= 0) l
        else l match {
            case Nil => Nil
            case Cons(_, t) => drop(t, n-1)
        }
    }

    def dropWhile[A](l: List[A], f: A => Boolean): List[A] = {
        l match {
            case Cons(h, t) if (f(h)) => dropWhile(t, f)
            case _ => l
        }
    }

    def init[A](l: List[A]): List[A] = {
        l match {
            case Nil => sys.error("init of empty list")
            case Cons(h, Nil) => Nil
            case Cons(h, t) => Cons(h, init(t))
        }
    }

    def foldRight[A,B](as: List[A], z: B)(f: (A, B) => B): B = {
        as match {
            case Nil => z
            case Cons(x, xs) => {
                f(x, foldRight(xs, z)(f))
            }
        }
    }

    def sum2(ns: List[Int]) = foldRight(ns, 0)((x, y) => x + y)
    
    def product2(ns: List[Double]) = foldRight(ns, 1.0)(_ * _)

    def length[A](as: List[A]): Int = foldRight(as, 0)((x, y) => 1 + y)

    @annotation.tailrec
    def foldLeft[A, B](as: List[A], z: B)(f: (B, A) => B): B = {
        as match {
            case Nil => z
            case Cons(x, xs) => {
                foldLeft(xs, f(z, x))(f)
            }
        }
    }

    def sumL(ns: List[Int]) = foldLeft(ns, 0)((x, y) => x + y)
    
    def productL(ns: List[Double]) = foldLeft(ns, 1.0)(_ * _)

    def lengthL[A](as: List[A]): Int = foldLeft(as, 0)((x, y) => x + 1)

    def reverse[A](as: List[A]): List[A] = foldLeft(as, List[A]())((acc, h) => Cons(h, acc))

    def foldRightL[A, B](as: List[A], z: B)(f: (A, B) => B): B = foldLeft(List.reverse(as), z)((b, a) => f(a, b))

    def foldRightL2[A, B](as: List[A], z: B)(f: (A, B) => B): B = {
        foldLeft(as, (b: B) => b)((g, a) => b => g(f(a, b)))(z)
    }

    def f[A, B](as: List[A], z: B)(f: (A, B) => B) = {
        List.foldLeft(as, (b: B) => b)((g, a) => b => g(f(a, b)))
    }
}

List.foldRightL2(List(1,2,3),0)(_+_)