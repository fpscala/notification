package uz.scala.notification

import japgolly.scalajs.react._
import japgolly.scalajs.react.component.Scala.Unmounted
import japgolly.scalajs.react.vdom.html_<^._
import org.scalajs.dom.html.Div
import scalacss.ScalaCssReact._
import uz.scala.notification.NotificationStyle._

import scala.concurrent.duration._
import scala.scalajs.js.timers._
import scala.util.Random
object Notification {

  case class Toast(`type`: NotificationType, icon: String, message: String, title: String)

  case class ToastState(toasts: Map[String, Toast] = Map.empty, duration: FiniteDuration)
  class Backend($ : BackendScope[_, ToastState]) {
    def genToastrId: String = Random.alphanumeric.take(5).mkString

    def toastMsg(`type`: NotificationType, icon: String, message: String, title: String): Callback = {
      val id       = genToastrId
      val newToast = Toast(`type`, icon, message, title)
      $.modState { s =>
        deleteToastAfterTimeout(id, s.duration)
        s.copy(toasts = s.toasts + (id -> newToast))
      }
    }

    def deleteToastAfterTimeout(id: String, duration: FiniteDuration): SetTimeoutHandle =
      setTimeout(duration) {
        $.modState(s => s.copy(toasts = s.toasts - id)).runNow()
      }

    def render(state: ToastState): VdomTagOf[Div] =
      <.div(notificationBox)(
        state.toasts.values.zipWithIndex
          .toVdomArray { case (toast, index) =>
            <.div(^.key := s"toast$index", notification, typeStyle(toast.`type`))(
             <.section(^.cls := "body")(
               <.i(^.cls := "fa " + toast.icon),
               <.span(title)(toast.title),
               <.p(toast.message)))
          }.when(state.toasts.nonEmpty))
  }

  private val component =
    ScalaComponent
      .builder[Int]
      .initialStateFromProps(p => ToastState(duration = p seconds))
      .renderBackend[Backend]
      .build

  private val toastRef = Ref.toScalaComponent(component)

  private def toast(`type`: NotificationType, icon: String, message: String, title: String): CallbackTo[Unit] =
    toastRef.get
      .map(_.backend.toastMsg(`type`, icon, message, title))
      .getOrElse(Callback.empty)
      .flatten

  def render(duration: Int = 6): Unmounted[Int, ToastState, Backend] = toastRef.component(duration)

  def warning(message: String): Callback =
    toast(Warn, "fa-exclamation-circle", message, "Notification")

  def info(message: String): Callback =
    toast(Info, "fa-info-circle", message, "Information")

  def success(message: String): Callback =
    toast(Success, "fa-check", message, "Successful")

  def error(message: String): Callback =
    toast(Fail, "fa-exclamation-triangle", message, "Error")

}
