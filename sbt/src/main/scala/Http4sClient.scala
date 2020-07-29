import cats.effect._
import org.http4s._
import org.http4s.client.blaze._
import org.http4s.dsl.io._
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

object Http4sClient {
    def main(args: Array[String]): Unit = {
        val req = Request[IO](Method.GET, Uri.uri("https://www.google.com"))
        val client = Http1Client[IO]().unsafeRunSync
        val googleGet = client.expect[String]("https://www.google.com")
        val status = client.status(req).unsafeRunSync
        // println(client.expect[String](req).unsafeRunSync)
        // client.expect[String](req).unsafeRunAsync(res => println(res.toOption.get))
        // println(client.expect[String](req).unsafeRunSync)
        // client.expect[String](Request[IO](Method.GET, Uri.uri("https://www.google.com"))).unsafeRunAsync(res => println(res.toOption.get))
        client.status(req).unsafeRunAsync(status => println(status)).
        client.status(req).unsafeToFuture.onSuccess(println(_))
    }
}