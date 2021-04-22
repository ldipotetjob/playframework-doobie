package controllers

import databases.model.{FootballMatch, Parseable}
import play.api.libs.json.{Json, Writes}
import play.api.mvc._
import play.mvc.Http

import scala.reflect.ClassTag

/** TODO: Internationalization of messages. */

/**
 * The Accept request HTTP header advertises which content types, expressed as MIME types, the client is able
 * to understand. Using content negotiation, the server then selects one of the proposals, uses it and informs the
 * client of its choice with the Content-Type response header.
 */

//ref.: http://jsonapi.org/format/#content-negotiation-clients
trait ContentNegotiation {

  /**
   * Self type annotation -> trait can be mixed into Controller without extends it
   * (thus, has access to render, Accepts and Ok and the rest of members
   * trait ControllerHelpers)
   */

  self: ControllerHelpers =>

  /**
   * Serializes the provided value according to the Accept headers of the (implicitly) provided request
   *
   * @param content   This param define what template must be used in csv response/json serialization
   * @param request
   * @param tag       solution for java type erasure
   * @param writeable json format for Json serialization
   * @tparam C
   * @return
   */
  def proccessContentNegotiation[C <: Parseable] (content: Seq[C])(implicit request: Request[AnyContent], tag: ClassTag[C], writeable: Writes[C]): Result = {
    val AcceptsCSV = Accepting("text/csv")
    render {
      // WARN  akka.actor.ActorSystemImpl - Explicitly set HTTP header 'Content-Type: text/csv' is ignored,
      // explicit `Content-Type` header is not allowed. Set `HttpResponse.entity.contentType` instead.
      case Accepts.Json() => Ok(
        Json.obj("status" -> "OK", "message" -> Json.toJson(content))).as(Http.MimeTypes.JSON)
      case AcceptsCSV() => Ok(selecTemplate[C](content)).as("text/csv")
      case _ => NotAcceptable(Json.obj(
        "status" ->
        NotAcceptable.header.status,
        "message" -> "Accept value can not be served, only serve txt/csv or application/json"))
    }
  }

  /**
   *
   * Serializes the provided value according to the Accept headers of the (implicitly) provided request
   *
   * @param content
   * @param request Remember that its depend of the request Body and REMEMBER tha the body is transformed in case
   *                of any validator
   * @param tag     preventing type erasure
   * @param writeable
   * @tparam C
   * @return
   */

  def processContentNegotiationForJson[C <: Parseable]
  (content: C)()
  (implicit request: Request[AnyContent], tag: ClassTag[C], writeable: Writes[C]): Result = {
    val AcceptTextPlain = Accepting("text/plain")
    render {
      case Accepts.Json() => Ok(
        Json.obj("status" -> "OK", "message" -> Json.toJson(content))).as(Http.MimeTypes.JSON)
      case AcceptTextPlain() => Ok("Process Done").withHeaders(CONTENT_TYPE -> "text/plain")
      case _ => NotAcceptable(
        Json.obj(
          "status" ->
            NotAcceptable.header.status,
          "message" -> "Accept value can not be served, only serve txt/csv or application/json"))
    }
  }

  /** Select the right csv template that must be returned with the response. */

  def selecTemplate[T] (genericSeq: Seq[T])(implicit tag: ClassTag[T]) = {

    extractRightCollection[T](genericSeq) match {
      /**
       * This match can NOT be generic. For every kind of model we need an specific template.
       * So you should have so many "Templates" and so many "Case" as report as you need.
       * Now we have only views.csv.football In real case you can have as many template as you need.
       *
       */
      case Some(pattern: FootballMatch) => {
        val matchseq: Seq[FootballMatch] = genericSeq.asInstanceOf[Seq[FootballMatch]]
        views.csv.football(matchseq)
      }
      case _ => views.csv.football(Seq[FootballMatch]())
    }
  }

  /**
   * Extractor method that let the caller check the type of the collection
   *
   * @param list sequence of T element
   * @param tag  for preventing type erasure
   * @tparam T kind of element
   * @return
   */

  def extractRightCollection[T] (list: Seq[Any])(implicit tag: ClassTag[T]): Option[T] =
    list.headOption match {
      case Some(element: T) => Some(element)
      case _ => fNone[T]
    }

  def fNone[T]: Option[T] = None

}