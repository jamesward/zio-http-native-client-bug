import zio.*
import zio.http.*

object App extends ZIOAppDefault:

  val app = Http.collectZIO[Request] {
    case Method.GET -> Path.root =>
      Client.request("https://repo1.maven.org/maven2/dev/zio/zio-http_3/").flatMap { response =>
        response.body.asString.map { body =>
          Response.html(body)
        }
      }.catchAll { t =>
        ZIO.succeed(Response(Status.InternalServerError, body = Body.fromString(t.getMessage)))
      }
  }

  def run =
    Server.serve(app.withDefaultErrorResponse).provide(Server.default, Client.default).exitCode