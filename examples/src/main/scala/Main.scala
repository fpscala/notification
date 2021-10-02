import japgolly.scalajs.react.ScalaComponent
import japgolly.scalajs.react.vdom.html_<^._
import org.scalajs.dom.document
import scalacss.toStyleSheetInlineJsOps
import uz.scala.notification.{Notification, NotificationStyle}
import uz.scala.notification.CssSettings._

object Main extends App {
    val MyComponent =
      ScalaComponent.builder[Unit]
       .render { _ =>
         <.div(
           Notification.render(),
           <.button(^.onClick --> Notification.info("This is information box!"))("Show info"),
           <.button(^.onClick --> Notification.warning("This is warning box!"))("Show warn"),
           <.button(^.onClick --> Notification.error("This is error box!"))("Show fail"),
           <.button(^.onClick --> Notification.success("This is success box!"))("Show success")
         )
       }
       .build

    NotificationStyle.addToDocument()
    MyComponent().renderIntoDOM(document.getElementById("app"))
}
