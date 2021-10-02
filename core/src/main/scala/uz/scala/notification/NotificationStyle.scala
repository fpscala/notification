package uz.scala.notification
import scalacss.internal.Keyframes
import uz.scala.notification.CssSettings._

import scala.concurrent.duration.DurationInt

object NotificationStyle extends StyleSheet.Inline {
  import dsl._

  val from: StyleA = keyframe(
    opacity(0),
    top(-100 px)
  )

  val to: StyleA = keyframe(
    opacity(1),
    top(20 px)
  )

  val creating: Keyframes = keyframes(
    (0%%) -> from,
    (100%%) -> to
  )

  val notificationBox: StyleA = style(
    position.fixed,
    top(0 px),
    right(0.px),
    padding(10 px),
    zIndex(9999)
  )

  val notification: StyleA = style(
    margin(0 px, auto),
    transition := "all 0.5s cubic-bezier(0.09, 0.71, 0.35, 0.94) 0s",
    animationName(creating),
    animationDuration(1 seconds),
    position.relative,
    top(20 px),
    right(20 px),
    fontSize(15 px),
    letterSpacing(0.3 px),
    padding(10 px, 20 px),
    border(1 px, solid, c"#ddd"),
    borderRadius(7 px),
    boxShadow := "2px 3px 10px 0 rgba(0, 0, 0, 0.13)",
    display.flex,
    minHeight(65 px),
    color :=! c"#FFF",
    width(400 px)
  )

  val title: StyleA = style(
    margin(0 px, 0 px, 0 px, 10 px),
    fontSize(18 px),
    fontWeight.bold
  )

  val success: StyleA = style(
    backgroundColor :=! c"#39DA8A",
    boxShadow := "2px 3px 10px 0 rgba(57, 218, 138, .4)"
  )

  val warn: StyleA = style(
    backgroundColor :=! c"#FDAC41",
    boxShadow := "2px 3px 10px 0 rgba(253, 172, 65, .4)"
  )

  val info: StyleA = style(
    backgroundColor :=! c"#00CFDD",
    boxShadow := "2px 3px 10px 0 rgba(0, 207, 221, .4)"
  )

  val fail: StyleA = style(
    backgroundColor :=! c"#FF5B5C"
  )

  val typeStyle: NotificationType => StyleA = {
    case Success => success
    case Warn    => warn
    case Fail    => fail
    case Info    => info
  }
}
