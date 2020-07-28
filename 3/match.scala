val x = List(1,2,3,4,5) match {
    case Cons(h, t) => h
    case _ => 102
}